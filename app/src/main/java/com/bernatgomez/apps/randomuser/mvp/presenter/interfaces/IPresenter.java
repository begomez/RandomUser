package com.bernatgomez.apps.randomuser.mvp.presenter.interfaces;


import com.bernatgomez.apps.randomuser.interfaces.IBusClient;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPBaseView;


/**
 * Common interface for presenter classes
 * Use generics to store the MVP View
 *
 * Created by bernatgomez on 09/09/2017.
 */
public interface IPresenter<T extends IMVPBaseView> extends IBusClient {

    public void attachView(T view);

    public void detachView();

    public T getView();
}
