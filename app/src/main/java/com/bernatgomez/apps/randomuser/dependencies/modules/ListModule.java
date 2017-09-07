package com.bernatgomez.apps.randomuser.dependencies.modules;

import com.bernatgomez.apps.randomuser.dependencies.scopes.PerActivity;
import com.bernatgomez.apps.randomuser.mvp.presenter.ListPresenter;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bernatgomez on 08/09/2017.
 */
@Module
public class ListModule {
    @PerActivity
    @Provides
    public ListPresenter provideListPresenter(Bus bus) {
        return new ListPresenter(bus);
    }
}
