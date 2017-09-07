package com.bernatgomez.apps.randomuser.utils;

import android.util.Log;

/**
 * Wrapper utilities to log both android info and error messages
 */
public abstract class Logger {

    /**
     * Log info msg
     *
     * @param tag
     * @param msg
     */
    public static void logMsg(String tag, String msg) {
        Log.i(tag, msg);
    }

    /**
     * Log error msg
     *
     * @param tag
     * @param errorMsg
     */
    public static void logError(String tag, String errorMsg, Exception e) {
        Log.e(tag, errorMsg, e);
    }
}
