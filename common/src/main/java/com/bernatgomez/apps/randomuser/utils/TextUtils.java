package com.bernatgomez.apps.randomuser.utils;


import com.bernatgomez.apps.randomuser.models.JavaLogger;

import java.util.logging.Logger;


/**
 * Utils to work with string
 *
 * Created by bernatgomez on 10/09/2017.
 */
public abstract class TextUtils {

    private static final String TAG = TextUtils.class.getSimpleName();

    private static final String SPACE = " ";


    /**
     * Capitalize given sentence
     *
     * @param sentence
     * @return
     */
    public static String capitalizeSentence(String sentence) {
        StringBuffer strb = new StringBuffer("");

        try {
            String[] parts = sentence.split("["+ SPACE + "]");

            for (String part : parts) {
                strb.append(capitalize(part));
                strb.append(SPACE);
            }

            return strb.toString().trim();

        } catch (Exception e) {
            JavaLogger.logError(TAG, "capitalizeSentence()", e);

            return sentence;
        }
    }

    /**
     * Capitalize given string
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        final int FIRST_CHAR = 0;
        final int SECOND_CHAR = 1;


        if (TextUtils.canBeCapitalized(str)) {
            return str.substring(FIRST_CHAR, SECOND_CHAR).toUpperCase() + str.substring(SECOND_CHAR);

        } else {
            return "";
        }
    }

    private static boolean canBeCapitalized(String str) {
        return str != null && str.length() >= 1;
    }
}
