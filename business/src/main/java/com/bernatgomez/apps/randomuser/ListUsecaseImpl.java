package com.bernatgomez.apps.randomuser;

import com.bernatgomez.apps.randomuser.forms.GetUsersForm;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.sources.RestDataSource;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class ListUsecaseImpl extends BaseUsecase implements IListUsecase {


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
        GetUsersForm form = new GetUsersForm(20);

        this.registerInBus();

        this.rest.getUsers(form);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// SUBSCRIPTIONS
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Subscribe
    public void onSuccess(Boolean data) {
        this.bus.post(data);
        this.unregisterFromBus();
    }

    @Subscribe
    public void onError(DataError error) {
        this.bus.post(error);
        this.unregisterFromBus();
    }

}
