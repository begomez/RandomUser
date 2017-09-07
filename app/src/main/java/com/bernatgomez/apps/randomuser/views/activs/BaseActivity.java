package com.bernatgomez.apps.randomuser.views.activs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.utils.Logger;


/**
 * Superclass activity
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    public static final int MAIN_CONTAINER = R.id.main_content;

    protected int layoutId = 0;


////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFECYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(this.layoutId);

        if (savedInstanceState == null) {
            this.launchContentFragment();
        }

        Logger.logMsg(TAG, "onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Logger.logMsg(TAG, "onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Logger.logMsg(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Logger.logMsg(TAG, "onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Logger.logMsg(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Logger.logMsg(TAG, "onPause()");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Hook to launch main fragment transaction on each activity
     *
     * Should be overriden by child classes
     */
    protected void launchContentFragment() {}
}
