package com.bernatgomez.apps.randomuser.application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;

import com.bernatgomez.apps.randomuser.persist.RandomUserDatabase;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;

public class RandomUserApplication extends Application {

    private static final String TAG = RandomUserApplication.class.getSimpleName();

    private RoomDatabase database;


////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFE CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate() {
        AndroidLogger.logMsg(TAG, "onCreate()");

        super.onCreate();

        this.createRepo();
    }

    private void createRepo() {
        new DBCreator().execute();
    }

    @Override
    public void onTrimMemory(int level) {
        AndroidLogger.logMsg(TAG, "onTrimMemory()");

        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        AndroidLogger.logMsg(TAG, "onTerminate()");

        super.onTerminate();

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// HELPERS
////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isDbCreated() {
        return this.database != null;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// INNER CLASSES
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Async task used to create random user repo
     */
    final class DBCreator extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            AndroidLogger.logMsg(TAG, "doInBackground()");

            try {
                database =
                    Room.databaseBuilder(getApplicationContext(), RandomUserDatabase.class, RandomUserDatabase.DB_NAME).build();

                return true;

            } catch (Exception e) {
                AndroidLogger.logError(TAG, "doInBackground()", e);

                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            AndroidLogger.logMsg(TAG, "onPostExecute(): " + result);
        }
    }

}
