package com.bernatgomez.apps.randomuser.dependencies.components;


import com.bernatgomez.apps.randomuser.dependencies.modules.ListModule;
import com.bernatgomez.apps.randomuser.dependencies.scopes.PerActivity;
import com.bernatgomez.apps.randomuser.views.fragments.ListFragment;

import dagger.Component;


/**
 * Created by bernatgomez on 08/09/2017.
 */
@PerActivity
@Component(dependencies = {AppComponent.class}, modules=ListModule.class)
public interface ListComponent {
    //public ListPresenter getListPresenter();

    public void inject(ListFragment fragment);
}
