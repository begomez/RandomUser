package com.bernatgomez.apps.randomuser.persist.holder;


import com.bernatgomez.apps.randomuser.persist.holder.interfaces.IHolder;
import com.bernatgomez.apps.randomuser.persist.repo.RandomUserDb;


/**
 * Singleton holding an instance of ROOM repository
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class UserDbHolder implements IHolder {
    private RandomUserDb db;
    private static UserDbHolder instance;


    private UserDbHolder() {}

    public RandomUserDb getDb() {
        return db;
    }

    public void setDb(RandomUserDb db) {
        this.db = db;
    }

    public boolean isDbCreated() {
        return this.db != null;
    }

    public static UserDbHolder getInstance() {
        if (UserDbHolder.instance == null) {
            UserDbHolder.instance = new UserDbHolder();
        }

        return UserDbHolder.instance;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// IHOLDER IMPL
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void freeResources() {
        if (this.db.isOpen()) {
            this.db.close();
        }
        this.db = null;
    }
}
