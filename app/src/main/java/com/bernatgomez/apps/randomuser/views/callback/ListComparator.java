package com.bernatgomez.apps.randomuser.views.callback;


import com.bernatgomez.apps.randomuser.models.UserModel;

import java.util.Comparator;


/**
 * Data comparator for random users list sorting
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class ListComparator implements Comparator<UserModel> {

    /**
     * Compare random user names
     *
     * @param user1
     * @param user2
     *
     * @return
     */
    @Override
    public int compare(UserModel user1, UserModel user2) {
        return user1.getName().getFullName().compareTo(user2.getName().getFullName());
    }
}
