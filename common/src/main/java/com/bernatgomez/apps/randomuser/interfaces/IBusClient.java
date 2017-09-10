package com.bernatgomez.apps.randomuser.interfaces;


/**
 * Common interface for objects that use an event bus
 *
 * Created by bernatgomez on 10/09/2017.
 */
public interface IBusClient {
    public void registerInBus();
    public void unregisterFromBus();
    public boolean isRegistered();
}
