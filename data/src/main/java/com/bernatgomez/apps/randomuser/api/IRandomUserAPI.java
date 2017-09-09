package com.bernatgomez.apps.randomuser.api;

import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.bernatgomez.apps.randomuser.models.DataUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit interface containing the methods that match the REST API published by randomuser web
 *
 * Created by bernatgomez on 08/09/2017.
 */
public interface IRandomUserAPI {

    @GET("/api")
    public Observable<DataResponse> getUsers(@Query("results") int results);
}
