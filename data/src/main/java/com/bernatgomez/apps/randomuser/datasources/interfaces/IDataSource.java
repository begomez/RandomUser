package com.bernatgomez.apps.randomuser.datasources.interfaces;


import com.bernatgomez.apps.randomuser.forms.FormGetUsers;


/**
 * Data source generic interface
 */
public interface IDataSource {
    public void getUsers(FormGetUsers form);
}
