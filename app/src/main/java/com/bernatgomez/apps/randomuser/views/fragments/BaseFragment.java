package com.bernatgomez.apps.randomuser.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.f2prateek.dart.Dart;

import butterknife.ButterKnife;

/**
 * Base class fragment defining a common architecture
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class BaseFragment extends Fragment {

    public static final String TAG = BaseFragment.class.getSimpleName();

    /**
     * Layout identifier
     */
    protected int layoutId = 0;


////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFECYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidLogger.logMsg(TAG, "onCreateView()");

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(this.layoutId, container, false);

        this.bindViews(v);

        this.bindExtras();

        this.configViews();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onStart()");

    }

    @Override
    public void onStop() {
        super.onStop();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onStop()");
    }

    @Override
    public void onResume() {
        super.onResume();

        this.fetchData();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();

        AndroidLogger.logMsg(this.getClass().getSimpleName(), "onPause()");

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Hook for Dagger injection
     *
     * Should be overriden by children
     */
    protected void injectDependencies() {}

    /**
     * Hook for data requests to REST repos (for instance)
     */
    protected void fetchData() {}

    protected void bindViews(View v) {
        ButterKnife.bind(this, v);
    }

    protected void bindExtras() {
        Dart.inject(this, this.getArguments());
    }

    /**
     * Hook used in order to set content in views
     */
    protected void configViews() {}

    /**
     * Accessor for fragment identifiers used in fragment transactions
     *
     * @return
     */
    public String getIdentifier() {
        return this.getClass().getName();
    }
}
