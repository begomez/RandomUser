package com.bernatgomez.apps.randomuser.models;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class DataUser extends BaseData {

    public DataUser(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = "";
}
