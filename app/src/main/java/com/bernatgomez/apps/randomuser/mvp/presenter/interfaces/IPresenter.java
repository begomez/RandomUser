package com.bernatgomez.apps.randomuser.mvp.presenter.interfaces;

import com.bernatgomez.apps.randomuser.mvp.view.IMVPBaseView;

/**
 * Common interface for presenter classes
 *
 * Created by bernatgomez on 09/09/2017.
 */
public interface IPresenter<T extends IMVPBaseView> {

    public void registerInBus();

    public void unregisterFromBus();

    public void attachView(T view);

    public void detachView();

    public T getView();
}
