package com.bernatgomez.apps.randomuser.mvp.view;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public interface IMVPListView extends IMVPBaseView {
    public void onRandomUsersReceived();
    public void onRandomUsersError();
}
