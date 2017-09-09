package com.bernatgomez.apps.randomuser.views.activs;


import android.support.annotation.Nullable;
import android.os.Bundle;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.utils.Constants;
import com.bernatgomez.apps.randomuser.utils.Navigator;
import com.bernatgomez.apps.randomuser.views.fragments.DetailFragment;
import com.f2prateek.dart.InjectExtra;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Detail screen container
 */
public class DetailActivity extends BaseActivity {

    @Nullable
    @InjectExtra(Constants.EXTRA_USER)
    protected UserModel user;


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

        Navigator.launchFragment(this, MAIN_CONTAINER, DetailFragment.newInstance(this.user));
    }
}
