package com.bernatgomez.apps.randomuser;

import com.bernatgomez.apps.randomuser.forms.GetUsersForm;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.bernatgomez.apps.randomuser.models.DataUser;
import com.bernatgomez.apps.randomuser.sources.RestDataSource;
import com.bernatgomez.apps.randomuser.utils.DataMapper;
import com.bernatgomez.apps.randomuser.utils.*;
import com.bernatgomez.apps.randomuser.utils.JavaLogger;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class ListUsecaseImpl extends BaseUsecase implements IListUsecase {

    private static final String TAG = ListUsecaseImpl.class.getSimpleName();


////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////////////////////////

    public ListUsecaseImpl(Bus bus, RestDataSource rest) {
        super(bus, rest);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void performAction() {
        GetUsersForm form = new GetUsersForm(5);

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
