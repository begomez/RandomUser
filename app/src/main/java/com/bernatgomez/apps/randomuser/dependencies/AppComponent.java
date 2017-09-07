package com.bernatgomez.apps.randomuser.dependencies;


import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


/**
 * Created by bernatgomez on 07/09/2017.
 */
@Singleton
@Component(modules=AppModule.class)
public interface AppComponent {
    public Bus getBus();
}
