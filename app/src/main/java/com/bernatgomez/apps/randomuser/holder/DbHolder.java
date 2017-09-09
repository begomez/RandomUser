package com.bernatgomez.apps.randomuser.holder;


import com.bernatgomez.apps.randomuser.persist.RandomUserDatabase;


/**
 * Singleton holding an instance of ROOM repository
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class DbHolder {
    private RandomUserDatabase db;
    private static DbHolder instance;


    private DbHolder() {}

    public RandomUserDatabase getDb() {
        return db;
    }

    public void setDb(RandomUserDatabase db) {
        this.db = db;
    }

    public boolean isDbCreated() {
        return this.db != null;
    }

    public static DbHolder getInstance() {
        if (DbHolder.instance == null) {
            DbHolder.instance = new DbHolder();
        }

        return DbHolder.instance;
    }
}
