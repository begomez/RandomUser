package com.bernatgomez.apps.randomuser.views.activs;

import android.os.Bundle;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.utils.Navigator;
import com.bernatgomez.apps.randomuser.views.fragments.ListFragment;


/**
 * Main screen container
 */
public class ListActivity extends BaseActivity {

////////////////////////////////////////////////////////////////////////////////////////////////////
// LIFECYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.layoutId = R.layout.activity;

        super.onCreate(savedInstanceState);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
// ARCH
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void launchContentFragment() {
        super.launchContentFragment();

        Navigator.launchFragment(this, MAIN_CONTAINER, ListFragment.newInstance());
    }
}
