package com.bernatgomez.apps.randomuser.utils;


/**
 * Utils to work with string
 *
 * Created by bernatgomez on 10/09/2017.
 */
public abstract class TextUtils {

    /**
     * Capitalize given string
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        if (str != null) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return "";
        }
    }
}
