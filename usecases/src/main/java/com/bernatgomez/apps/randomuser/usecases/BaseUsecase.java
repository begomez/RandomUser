package com.bernatgomez.apps.randomuser.usecases;


import com.bernatgomez.apps.randomuser.sources.interfaces.IDataSource;
import com.squareup.otto.Bus;


/**
 * Base usecase defining a common architecture
 * Created by bernatgomez on 08/09/2017.
 */
public class BaseUsecase {

    protected final Bus bus;
    protected final IDataSource rest;


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

    protected void registerInBus() {
        this.bus.register(this);
    }

    protected void unregisterFromBus() {
        this.bus.unregister(this);
    }
}
