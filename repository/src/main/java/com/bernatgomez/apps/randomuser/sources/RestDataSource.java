package com.bernatgomez.apps.randomuser.sources;

import com.bernatgomez.apps.randomuser.api.IRandomUserAPI;
import com.bernatgomez.apps.randomuser.forms.GetUsersForm;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataUser;
import com.bernatgomez.apps.randomuser.utils.JavaLogger;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class RestDataSource implements IDataSource {

    private static final String TAG = RestDataSource.class.getSimpleName();

    protected final Bus bus;
    protected Retrofit gateway;
    protected IRandomUserAPI api;


    public RestDataSource(Bus bus, Retrofit gateway) {
        this.bus = bus;
        this.gateway = gateway;

        this.createUsersAPI();
    }

    private void createUsersAPI() {
        this.api = this.gateway.create(IRandomUserAPI.class);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void getUsers(GetUsersForm form) {

        Call<List<DataUser>> call = this.api.getUsers(form.getResults());

        call.enqueue(new Callback<List<DataUser>>() {

            @Override
            public void onResponse(Call<List<DataUser>> call, Response<List<DataUser>> response) {
                JavaLogger.logMsg(TAG, "success");

                RestDataSource.this.bus.post(new Boolean(true));
            }

            @Override
            public void onFailure(Call<List<DataUser>> call, Throwable t) {
                JavaLogger.logError(TAG, "error", t);

                RestDataSource.this.bus.post(new DataError("kiwi"));
            }
        });

    }
}
