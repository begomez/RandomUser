package com.bernatgomez.apps.randomuser.usecases.users;


import com.bernatgomez.apps.randomuser.forms.FormGetUsers;
import com.bernatgomez.apps.randomuser.mappers.ErrorMapper;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.bernatgomez.apps.randomuser.sources.interfaces.IDataSource;
import com.bernatgomez.apps.randomuser.usecases.core.BaseUsecase;
import com.bernatgomez.apps.randomuser.mappers.DataMapper;
import com.bernatgomez.apps.randomuser.models.JavaLogger;
import com.bernatgomez.apps.randomuser.usecases.core.IBaseUsecase;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


/**
 * Random users usecase
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class GetUsersUsecaseImpl extends BaseUsecase {

    private static final String TAG = GetUsersUsecaseImpl.class.getSimpleName();


////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////

    public GetUsersUsecaseImpl(Bus bus, IDataSource rest) {
        super(bus, rest);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void performAction() {
        FormGetUsers form = new FormGetUsers();

        this.registerInBus();

        this.rest.getUsers(form);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// SUBSCRIPTIONS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Subscribe
    public void onSuccess(DataResponse data) {
        JavaLogger.logMsg(TAG, data.toString());

        this.bus.post(DataMapper.mapUsers(data.getResults()));

        this.unregisterFromBus();
    }

    @Subscribe
    public void onError(DataError error) {
        JavaLogger.logMsg(TAG, error.getMsg());

        this.bus.post(ErrorMapper.mapError(error));

        this.unregisterFromBus();
    }

}
