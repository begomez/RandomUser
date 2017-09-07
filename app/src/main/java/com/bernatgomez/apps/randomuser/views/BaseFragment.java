package com.bernatgomez.apps.randomuser.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bernatgomez.apps.randomuser.utils.Logger;

import butterknife.ButterKnife;

/**
 * Superclass fragment
 *
 * Created by bernatgomez on 08/09/2017.
 */
public class BaseFragment extends Fragment {

    public static final String TAG = BaseFragment.class.getSimpleName();

    protected int layoutId = 0;


////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFECYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logMsg(TAG, "onCreateView()");

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(this.layoutId, container, false);

        this.bindViews(v);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.logMsg(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Logger.logMsg(TAG, "onDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();

        Logger.logMsg(TAG, "onStart()");

    }

    @Override
    public void onStop() {
        super.onStop();

        Logger.logMsg(TAG, "onStop()");
    }

    @Override
    public void onResume() {
        super.onResume();

        Logger.logMsg(TAG, "onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();

        Logger.logMsg(TAG, "onPause()");

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCHITECTURE
////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void bindViews(View v) {
        ButterKnife.bind(this, v);
    }

    public String getIdentifier() {
        return this.getClass().getName();
    }
}
