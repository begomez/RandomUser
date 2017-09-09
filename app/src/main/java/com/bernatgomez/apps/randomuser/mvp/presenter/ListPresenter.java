package com.bernatgomez.apps.randomuser.mvp.presenter;

import com.bernatgomez.apps.randomuser.usecases.users.IGetUsersUsecase;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import com.bernatgomez.apps.randomuser.models.ErrorModel;
import com.bernatgomez.apps.randomuser.models.UserModel;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class ListPresenter extends BasePresenter<IMVPListView> {

    protected IGetUsersUsecase usecase;


////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Inject
    public ListPresenter(Bus bus, IGetUsersUsecase usecase) {
        super(bus);

        this.usecase = usecase;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// API
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Fetch users from remote repo
     */
    public void getRandomUsers() {
        this.getView().showLoading();

        this.usecase.performAction();
    }

    /**
     * Filter users according to user entered query
     * @param query
     */
    public void filterUsers(String query) {
        this.view.getAdapter().filter(query);

        this.view.resetScroll();
    }

    /**
     * Disable item when user selects the corresponding action
     *
     * @param user
     */
    public void disableUser(UserModel user) {

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
