package com.bernatgomez.apps.randomuser.sources;

import com.bernatgomez.apps.randomuser.api.IRandomUserAPI;
import com.bernatgomez.apps.randomuser.forms.GetUsersForm;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.bernatgomez.apps.randomuser.models.DataUser;
import com.bernatgomez.apps.randomuser.utils.JavaLogger;
import com.squareup.otto.Bus;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.xml.crypto.Data;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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

                   RestDataSource.this.bus.post(new DataError(""));
               }

               @Override
               public void onNext(DataResponse dataResponse) {
                   RestDataSource.this.bus.post(new Boolean(true));

                   JavaLogger.logMsg(TAG, "onNext()");
               }
           });
    }
}
