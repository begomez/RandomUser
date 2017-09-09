package com.bernatgomez.apps.randomuser.mvp.view;

import java.util.List;

import models.ErrorModel;
import models.UserModel;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public interface IMVPListView extends IMVPBaseView {
    public void onRandomUsersReceived(List<UserModel> users);
    public void onRandomUsersError(String msg);
}
