package com.bernatgomez.apps.randomuser.mvp.presenter;

import com.bernatgomez.apps.randomuser.IListUsecase;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.ErrorModel;
import models.UserModel;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class ListPresenter extends BasePresenter<IMVPListView> {

    protected IListUsecase usecase;


////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Inject
    public ListPresenter(Bus bus, IListUsecase usecase) {
        super(bus);

        this.usecase = usecase;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// API
////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getRandomUsers() {
        this.getView().showLoading();

        this.usecase.performAction();
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// SUBSCRIPTIONS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Subscribe
    public void onSuccess(ArrayList<UserModel> data) {
        AndroidLogger.logMsg(TAG, data.toString());

        this.getView().hideLoading();

        this.getView().onRandomUsersReceived(data);
    }

    @Subscribe
    public void onError(ErrorModel error) {
        AndroidLogger.logError(TAG, error.getMsg(), null);

        this.getView().hideLoading();

        this.getView().onRandomUsersError(error.getMsg());
    }

}
