package com.bernatgomez.apps.randomuser.application;

import android.app.Application;

import com.bernatgomez.apps.randomuser.utils.AndroidLogger;

public class RandomUserApplication extends Application {

    private static final String TAG = RandomUserApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidLogger.logMsg(TAG, "onCreate()");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        AndroidLogger.logMsg(TAG, "onTrimMemory()");

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        AndroidLogger.logMsg(TAG, "onTerminate()");
    }


}
