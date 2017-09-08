package com.bernatgomez.apps.randomuser.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerAppComponent;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerListComponent;
import com.bernatgomez.apps.randomuser.dependencies.modules.ListModule;
import com.bernatgomez.apps.randomuser.mvp.presenter.ListPresenter;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by bernatgomez on 08/09/2017.
 */
public class ListFragment extends BaseFragment implements IMVPListView {

    @BindView(R.id.txtSample)
    protected TextView txtSample;

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
    public void onRandomUsersReceived() {
        AndroidLogger.logMsg(TAG, "succcess");
        //TODO
    }

    @Override
    public void onRandomUsersError() {
        AndroidLogger.logError(TAG, "error", null);
        //TODO
    }
}
