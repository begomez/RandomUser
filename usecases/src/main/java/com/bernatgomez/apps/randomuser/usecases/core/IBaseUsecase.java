package com.bernatgomez.apps.randomuser.usecases.core;


import com.bernatgomez.apps.randomuser.interfaces.IBusClient;


/**
 * Interface containing common usecase operations
 */
public interface IBaseUsecase extends IBusClient {
    public void performAction();
}
