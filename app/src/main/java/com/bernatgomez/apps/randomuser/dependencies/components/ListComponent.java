package com.bernatgomez.apps.randomuser.dependencies.components;


import com.bernatgomez.apps.randomuser.dependencies.modules.ListModule;
import com.bernatgomez.apps.randomuser.dependencies.scopes.PerActivity;
import com.bernatgomez.apps.randomuser.mvp.presenter.ListPresenter;
import com.bernatgomez.apps.randomuser.persist.transactions.DbTransactionExecutor;
import com.bernatgomez.apps.randomuser.usecases.users.IGetUsersUsecase;
import com.bernatgomez.apps.randomuser.views.fragments.ListFragment;

import dagger.Component;


/**
 * Usecase level component used to indicate in which classes depedency injection takes place
 *
 * Created by bernatgomez on 08/09/2017.
 */
@PerActivity
@Component(dependencies = {AppComponent.class}, modules=ListModule.class)
public interface ListComponent {
    public void inject(ListFragment fragment);
    public IGetUsersUsecase getUsecase();
    public ListPresenter getPresenter();
    public DbTransactionExecutor getExecutor();
}
