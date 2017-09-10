package com.bernatgomez.apps.randomuser.mvp.presenter;


import com.bernatgomez.apps.randomuser.mvp.presenter.interfaces.IPresenter;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPBaseView;
import com.squareup.otto.Bus;


/**
 * Presenter generalization containing commom operations for presenters
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class BasePresenter<T extends IMVPBaseView> implements IPresenter<T> {

    public static final String TAG = BasePresenter.class.getSimpleName();

    protected Bus bus;
    protected T view;

    protected boolean registered = false;


    public BasePresenter(Bus bus) {
        super();

        this.bus = bus;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Should be overriden by children to instantiante corresponding usecase
     */
    protected void initUsecase() {}

    @Override
    public void registerInBus() {
        this.bus.register(this);
        this.registered = true;
    }

    @Override
    public void unregisterFromBus() {
        this.bus.unregister(this);
        this.registered = false;
    }

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public T getView() {
        return this.view;
    }

    @Override
    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
