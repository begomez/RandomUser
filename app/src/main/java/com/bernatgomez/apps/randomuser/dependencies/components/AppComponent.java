package com.bernatgomez.apps.randomuser.dependencies.components;


import com.bernatgomez.apps.randomuser.sources.IDataSource;
import com.bernatgomez.apps.randomuser.dependencies.modules.AppModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by bernatgomez on 07/09/2017.
 */
@Singleton
@Component(modules=AppModule.class)
public interface AppComponent {
    public Bus getBus();
    public IDataSource getDataSource();
}
