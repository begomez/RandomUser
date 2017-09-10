package com.bernatgomez.apps.randomuser.persist.holder;


import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.persist.holder.interfaces.IHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton holding discarded users

 * Created by bernatgomez on 10/09/2017.
 */

public class DiscardedUsersHolder implements IHolder {

    private static DiscardedUsersHolder instance;

    private List<UserModel> discardedUsers;


    private DiscardedUsersHolder() {
        this.initComponents();
    }

    private void initComponents() {
        this.discardedUsers = new ArrayList<UserModel>();
    }

    public void addDiscardedUser(UserModel target) {
        if (!this.discardedUsers.contains(target)) {
            this.discardedUsers.add(target);
        }
    }

    public List<UserModel> getDiscardedUsers() {
        return discardedUsers;
    }

    public void setDiscardedUsers(List<UserModel> discardedUsers) {
        this.discardedUsers = discardedUsers;
    }

    /**
     *
     * @return
     */
    public static DiscardedUsersHolder getInstance() {
        if (DiscardedUsersHolder.instance == null) {
            DiscardedUsersHolder.instance = new DiscardedUsersHolder();
        }

        return DiscardedUsersHolder.instance;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// IHOLDER IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void freeResources() {
        this.discardedUsers.clear();

        this.discardedUsers = null;
    }
}
