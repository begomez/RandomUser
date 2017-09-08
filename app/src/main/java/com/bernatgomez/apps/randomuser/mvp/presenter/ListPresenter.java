package com.bernatgomez.apps.randomuser.mvp.presenter;

import com.bernatgomez.apps.randomuser.IListUsecase;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

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
    public void onSuccess(Boolean data) {
        this.getView().hideLoading();

        this.getView().onRandomUsersReceived();
    }

    @Subscribe
    public void onError(DataError error) {
        this.getView().hideLoading();

        this.getView().onRandomUsersError();
    }

}
