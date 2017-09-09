package com.bernatgomez.apps.randomuser.persist.repo;


import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;


/**
 * Random user persistence mechanism implemented through architecture components
 *
 * Created by bernatgomez on 09/09/2017.
 */
@Database(entities={User.class}, version= RandomUserDb.DB_VERSION)
public abstract class RandomUserDb extends RoomDatabase {

    public static final int DB_VERSION = 1;

    public static final String DB_NAME= "random_user";


    /**
     * Accessor for database interface
     * @return
     */
    public abstract IRandomUserDao userDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
