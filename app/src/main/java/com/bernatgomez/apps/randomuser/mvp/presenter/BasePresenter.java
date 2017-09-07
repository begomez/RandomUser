package com.bernatgomez.apps.randomuser.mvp.presenter;

import com.bernatgomez.apps.randomuser.mvp.view.IMVPBaseView;
import com.squareup.otto.Bus;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class BasePresenter<T extends IMVPBaseView> {

    public static final String TAG = BasePresenter.class.getSimpleName();

    protected Bus bus;
    protected T view;


    public BasePresenter(Bus bus) {
        super();

        this.bus = bus;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    public void registerInBus() {
        this.bus.register(this);
    }

    public void unregisterFromBus() {
        this.bus.unregister(this);
    }

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }
}
