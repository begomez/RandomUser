package com.bernatgomez.apps.randomuser.forms;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public class GetUsersForm extends BaseForm {

    public GetUsersForm(int results) {
        this.results = results;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    private int results;
}
