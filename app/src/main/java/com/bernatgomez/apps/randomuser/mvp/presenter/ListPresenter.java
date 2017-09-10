package com.bernatgomez.apps.randomuser.mvp.presenter;

import com.bernatgomez.apps.randomuser.models.DbTransactionResult;
import com.bernatgomez.apps.randomuser.persist.holder.DiscardedUsersHolder;
import com.bernatgomez.apps.randomuser.persist.transactions.IExecutor;
import com.bernatgomez.apps.randomuser.persist.transactions.DbTransactionExecutor;
import com.bernatgomez.apps.randomuser.usecases.core.IBaseUsecase;
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

    protected IBaseUsecase usecase;

    @Inject
    protected DbTransactionExecutor executor;

////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Inject
    public ListPresenter(Bus bus, IBaseUsecase usecase, DbTransactionExecutor executor) {
        super(bus);

        this.usecase = usecase;

        this.executor = executor;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// API
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Fetch users from remote repo
     */
    public void getRandomUsers(boolean showLoading) {
        if (showLoading) {
            this.getView().showLoading();
        }
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
        DiscardedUsersHolder.getInstance().addDiscardedUser(user);

        this.view.disableUser(user);

        this.executor.execute(IExecutor.CmdType.DISABLE_USER, user);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// SUBSCRIPTIONS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Subscribe
    public void onOperationResult(DbTransactionResult result) {
        AndroidLogger.logMsg(TAG, "onOperationResult()" + result);

        //XXX: do nothing for now...
    }

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
