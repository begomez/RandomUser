package com.bernatgomez.apps.randomuser.persist.holder.interfaces;


import com.bernatgomez.apps.randomuser.persist.repo.RandomUserDb;


/**
 * Common interface for data holders
 *
 * Created by bernatgomez on 11/09/2017.
 */
public interface IHolder {

    /**
     * Nullify saved objects
     */
    public void freeResources();
}
