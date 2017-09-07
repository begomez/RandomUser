package com.bernatgomez.apps.randomuser.views;

import android.os.Bundle;

import com.bernatgomez.apps.randomuser.R;
import com.bernatgomez.apps.randomuser.utils.Navigator;

public class ListActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.layoutId = R.layout.activity_list;

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void launchContentFragment() {
        super.launchContentFragment();

        Navigator.launchFragment(this, MAIN_CONTAINER, ListFragment.newInstance());
    }
}
