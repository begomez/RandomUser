package com.bernatgomez.apps.randomuser.usecases.core;


import com.bernatgomez.apps.randomuser.datasources.interfaces.IDataSource;
import com.squareup.otto.Bus;


/**
 * Base usecase defining a common architecture
 * Created by bernatgomez on 08/09/2017.
 */
public abstract class BaseUsecase implements IBaseUsecase {

    protected final Bus bus;
    protected final IDataSource rest;
    protected boolean registered = false;


    /**
     * Constructor
     *
     * @param bus
     * @param rest
     */
    public BaseUsecase(Bus bus, IDataSource rest) {
        this.bus = bus;
        this.rest = rest;
    }

    @Override
    public void registerInBus() {
        this.bus.register(this);
        this.registered = true;
    }

    @Override
    public void unregisterFromBus() {
        if (this.isRegistered()) {
            this.bus.unregister(this);
            this.registered = false;
        }
    }

    /**
     * Should be overriden by children to perform specific actions
     */
    @Override
    public abstract void performAction();

    @Override
    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
