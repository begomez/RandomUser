package com.bernatgomez.apps.randomuser.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerAppComponent;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerListComponent;
import com.bernatgomez.apps.randomuser.dependencies.modules.ListModule;
import com.bernatgomez.apps.randomuser.mvp.presenter.ListPresenter;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.bernatgomez.apps.randomuser.utils.Navigator;
import com.bernatgomez.apps.randomuser.views.adapters.ListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import models.UserModel;


/**
 * Main screen fragment showing a list of items
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class ListFragment extends BaseFragment implements IMVPListView, ListAdapter.OnImageListener {

    @BindView(R.id.list)
    protected RecyclerView userList;

    @BindView(android.R.id.empty)
    protected View txtEmpty;

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
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void configViews() {
        this.userList.setHasFixedSize(false);
        this.userList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void fetchData() {
        super.fetchData();

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
    public void showLoading() {
        //TODO
    }

    @Override
    public void hideLoading() {
        //TODO
    }

    @Override
    public void onRandomUsersReceived(List<UserModel> users) {
        AndroidLogger.logMsg(TAG, "success");

        this.updateData(users);

    }

    @Override
    public void onRandomUsersError(String msg) {
        AndroidLogger.logError(TAG, msg, null);

    }

    private void updateData(final List<UserModel> users) {

        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListFragment.this.userList.setAdapter(new ListAdapter(users, ListFragment.this));
            }
        });
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onImageClick(UserModel user) {
        Navigator.launchDetail(this.getContext(), user);
    }
}
