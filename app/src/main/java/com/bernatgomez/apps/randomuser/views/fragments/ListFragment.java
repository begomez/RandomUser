package com.bernatgomez.apps.randomuser.views.fragments;


import android.app.SearchManager;
import android.app.Service;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;

import com.bernatgomez.apps.randomuser.dependencies.components.DaggerAppComponent;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerListComponent;
import com.bernatgomez.apps.randomuser.dependencies.modules.ListModule;
import com.bernatgomez.apps.randomuser.mvp.presenter.ListPresenter;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.bernatgomez.apps.randomuser.utils.Navigator;
import com.bernatgomez.apps.randomuser.views.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.views.callback.ListComparator;


/**
 * Main screen fragment showing a list of items
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class ListFragment extends BaseFragment implements IMVPListView, ListAdapter.OnImageListener {

    @BindView(R.id.swipe_container)
    protected SwipeRefreshLayout swipeContainer;

    @BindView(android.R.id.list)
    protected RecyclerView userList;

    @BindView(android.R.id.empty)
    protected TextView txtEmpty;

    protected SearchView searchView;

    protected ListAdapter adapter;

    @Inject
    protected ListPresenter presenter;


////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ListFragment newInstance() {
        return new ListFragment();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFE CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.layoutId = R.layout.fragment_list;

        this.setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        this.presenter.attachView(this);

        this.presenter.registerInBus();
    }

    @Override
    public void onStop() {
        super.onStop();

        this.presenter.unregisterFromBus();
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// SEARCH
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search_options, menu);

        this.prepareSearch(menu);
    }

    private void prepareSearch(final Menu menu) {
        SearchManager mgr = (SearchManager) this.getActivity().getSystemService(Service.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));


        searchView.setSearchableInfo(mgr.getSearchableInfo(this.getActivity().getComponentName()));

        searchView.setSubmitButtonEnabled(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setIconified(true);

                menu.findItem(R.id.action_search).collapseActionView();

                ListFragment.this.presenter.filterUsers(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void configViews() {
        this.configList();
        this.configSwipe();
    }

    private void configSwipe() {
        this.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
            }
        });
    }

    private void configList() {
        this.adapter = new ListAdapter(new ArrayList<UserModel>(), this, new ListComparator());

        this.userList.setHasFixedSize(false);
        this.userList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        this.userList.setAdapter(this.adapter);
    }

    @Override
    protected void loadData() {
        super.loadData();

        this.getUsers();
    }

    private void getUsers() {
        this.presenter.getRandomUsers();
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();

        //TODO: provide app component from application
        DaggerListComponent.builder().appComponent(DaggerAppComponent.create()).listModule(new ListModule()).build().inject(this);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// MVP
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onRandomUsersReceived(List<UserModel> users) {

        this.updateData(users);

    }

    @Override
    public void onRandomUsersError(String msg) {
        this.txtEmpty.setText(msg);

    }

    @Override
    public void resetScroll() {
        this.userList.scrollToPosition(0);
    }

    @Override
    public ListAdapter getAdapter() {
        return this.adapter;
    }

    private void updateData(final List<UserModel> users) {

        //XXX: run on uithread cause otto bus is not delivering in main thread
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListFragment.this.swipeContainer.setRefreshing(false);

                ListFragment.this.adapter.resetAdapter(users);
            }
        });
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onUserSelected(UserModel user) {
        AndroidLogger.logMsg(TAG, "onUserSelected()");

        Navigator.launchDetail(this.getContext(), user);
    }

    @Override
    public void onUserDisabled(UserModel user) {
        AndroidLogger.logMsg(TAG, "onUserDisabled");

        this.presenter.disableUser(user);
    }
}
