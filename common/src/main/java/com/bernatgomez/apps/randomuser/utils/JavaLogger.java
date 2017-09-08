package com.bernatgomez.apps.randomuser.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class JavaLogger {

    public static void logMsg(String tag, String msg) {
        Logger.getLogger(tag).log(Level.INFO, msg);
    }

    public static void logError(String tag, String errorMsg, Throwable e) {
        Logger.getLogger(tag).log(Level.SEVERE, errorMsg, e);
    }
}
