package com.bernatgomez.apps.randomuser.utils;


import android.content.Context;
import android.widget.Toast;

import com.bernatgomez.apps.randomuser.R;


/**
 * Utils class to show msg and alerts
 *
 * Created by bernatgomez on 10/09/2017.
 */
public abstract class MsgUtils {

    /**
     * Show a long toast
     *
     * @param cntxt
     */
    public static void showMsg(Context cntxt) {
        Toast.makeText(cntxt, cntxt.getResources().getString(R.string.list_info_msg), Toast.LENGTH_LONG).show();
    }
}
