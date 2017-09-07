package com.bernatgomez.apps.randomuser.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bernatgomez.apps.randomuser.views.activs.BaseActivity;
import com.bernatgomez.apps.randomuser.views.fragments.BaseFragment;

/**
 * Created by bernatgomez on 08/09/2017.
 */

public abstract class Navigator {

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
