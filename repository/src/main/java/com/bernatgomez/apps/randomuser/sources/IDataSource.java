package com.bernatgomez.apps.randomuser.sources;

import com.bernatgomez.apps.randomuser.forms.GetUsersForm;

public interface IDataSource {
    public void getUsers(GetUsersForm form);
}
