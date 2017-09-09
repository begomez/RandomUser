package com.bernatgomez.apps.randomuser.dependencies.modules;

import com.bernatgomez.apps.randomuser.sources.interfaces.IDataSource;
import com.bernatgomez.apps.randomuser.persist.transactions.DbTransactionExecutor;
import com.bernatgomez.apps.randomuser.usecases.users.IGetUsersUsecase;
import com.bernatgomez.apps.randomuser.usecases.users.GetUsersUsecaseImpl;
import com.bernatgomez.apps.randomuser.dependencies.scopes.PerActivity;
import com.bernatgomez.apps.randomuser.mvp.presenter.ListPresenter;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides specific dependencies for the list usecase
 *
 * Created by bernatgomez on 08/09/2017.
 */
@Module
public class ListModule {

    @PerActivity
    @Provides
    public IGetUsersUsecase provideListUsecase(Bus bus, IDataSource rest) {
        return new GetUsersUsecaseImpl(bus, rest);
    }

    @PerActivity
    @Provides
    public ListPresenter provideListPresenter(Bus bus, IGetUsersUsecase usecase, DbTransactionExecutor executor) {
        return new ListPresenter(bus, usecase, executor);
    }
}
