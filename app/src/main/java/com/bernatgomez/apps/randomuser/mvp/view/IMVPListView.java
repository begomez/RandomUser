package com.bernatgomez.apps.randomuser.mvp.view;


import android.support.v7.widget.RecyclerView;

import java.util.List;

import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.views.adapters.ListAdapter;


/**
 * Specific MVPView used in the users list
 *
 * Created by bernatgomez on 08/09/2017.
 */
public interface IMVPListView extends IMVPBaseView {
    public void onRandomUsersReceived(List<UserModel> users);
    public void onRandomUsersError(String msg);
    public void resetScroll();
    public ListAdapter getAdapter();
    public void disableUser(UserModel user);
}
