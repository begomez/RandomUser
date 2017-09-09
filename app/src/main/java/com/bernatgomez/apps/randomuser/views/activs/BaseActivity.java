package com.bernatgomez.apps.randomuser.views.activs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.bernatgomez.apps.randomuser.views.interfaces.ILoading;
import com.f2prateek.dart.Dart;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Base class activity defining a common architecture
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class BaseActivity extends AppCompatActivity implements ILoading {

    public static final String TAG = BaseActivity.class.getSimpleName();

    /**
     * Fragment transaction container
     */
    public static final int MAIN_CONTAINER = R.id.main_content;

    /**
     * Layout identifier for each activity
     */
    protected int layoutId = 0;

    @BindView(R.id.loading)
    protected ProgressBar loading;


////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFECYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(this.layoutId);

        this.injectViews();

        this.injectExtras();

        if (savedInstanceState == null) {
            this.launchContentFragment();
        }

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onPause()");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     */
    protected void injectViews() {
        ButterKnife.bind(this);
    }

    /**
     * Hook for extras injection
     */
    protected void injectExtras() {Dart.inject(this);}

    /**
     * Hook to launch main fragment transaction on each activity
     *
     * Should be overriden by child classes
     */
    protected void launchContentFragment() {}

////////////////////////////////////////////////////////////////////////////////////////////////////
// LOADING INTERFACE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void showLoading() {

        //XXX: run on uithread cause otto bus is not delivering in main thread
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (BaseActivity.this.loading != null) {
                    BaseActivity.this.loading.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void hideLoading() {

        //XXX: run on uithread cause otto bus is not delivering in main thread
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (BaseActivity.this.loading != null) {
                    BaseActivity.this.loading.setVisibility(View.GONE);
                }
            }
        });
    }

}
