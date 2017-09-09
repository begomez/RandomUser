package com.bernatgomez.apps.randomuser.sources;

import com.bernatgomez.apps.randomuser.forms.FormGetUsers;

public interface IDataSource {
    public void getUsers(FormGetUsers form);
}
