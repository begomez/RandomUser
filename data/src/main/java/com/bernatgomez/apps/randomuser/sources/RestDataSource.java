package com.bernatgomez.apps.randomuser.sources;

import com.bernatgomez.apps.randomuser.api.IRandomUserAPI;
import com.bernatgomez.apps.randomuser.forms.FormGetUsers;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataResponse;

import com.bernatgomez.apps.randomuser.models.JavaLogger;
import com.squareup.otto.Bus;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Rest datasource implementation wrapping randomuser API operations
 *
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
    public void getUsers(FormGetUsers form) {

        this.api.getUsers(form.getResults())

            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.immediate())

            .subscribe(new Subscriber<DataResponse>() {

               @Override
               public void onCompleted() {
                   JavaLogger.logMsg(TAG, "onCompleted()");
               }

               @Override
               public void onError(Throwable e) {
                   JavaLogger.logError(TAG, "onError()", e);

                   RestDataSource.this.bus.post(new DataError(e.getMessage()));
               }

               @Override
               public void onNext(DataResponse dataResponse) {
                   JavaLogger.logMsg(TAG, "onNext()");

                   RestDataSource.this.bus.post(dataResponse);
               }
           });
    }
}
