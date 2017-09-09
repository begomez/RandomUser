package com.bernatgomez.apps.randomuser.utils;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bernatgomez.apps.randomuser.views.activs.BaseActivity;
import com.bernatgomez.apps.randomuser.views.activs.DetailActivity;
import com.bernatgomez.apps.randomuser.views.fragments.BaseFragment;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Utils class used to navigate between activities and launch fragment transactions
 *
 * Created by bernatgomez on 08/09/2017.
 */
public abstract class Navigator {

    /**
     * Navigate to detail activity
     *
     * @param cntxt
     * @param user
     */
    public static void launchDetail(Context cntxt, UserModel user) {
        Intent i = new Intent(cntxt, DetailActivity.class);

        i.putExtra(Constants.EXTRA_USER, user);

        cntxt.startActivity(i);
    }

    /**
     * Method that encapsulates dynamic fragment transactions
     *
     * @param activ  Parent activity
     * @param container Container in which transaction takes place
     * @param fragment Target view
     */
    public static void launchFragment(BaseActivity activ, int container, BaseFragment fragment) {
        FragmentManager mgr = activ.getSupportFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();


        trans.replace(container, fragment, fragment.getIdentifier());

        trans.commit();
        mgr.executePendingTransactions();
    }
}
