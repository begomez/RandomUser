package com.bernatgomez.apps.randomuser.utils;


import com.bernatgomez.apps.randomuser.models.JavaLogger;

import java.util.logging.Logger;


/**
 * Utils to work with strings
 *
 * Created by bernatgomez on 10/09/2017.
 */
public abstract class TextUtils {

    private static final String TAG = TextUtils.class.getSimpleName();

    private static final String SPACE = " ";


    /**
     * Check whether a given string is not null and contains some text
     *
     * @param str
     * @return
     */
    public static boolean isValidAndNotEmptyString(String str) {
        return str != null && !str.trim().isEmpty();
    }

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
            return str.trim().substring(FIRST_CHAR, SECOND_CHAR).toUpperCase() + str.substring(SECOND_CHAR);

        } else {
            return "";
        }
    }

    /**
     * Check if capitalization is possible on given string
     *
     * @param str
     * @return
     */
    public static boolean canBeCapitalized(String str) {
        final int MIN_LENGTH = 1;

        return str != null && str.trim().length() >= MIN_LENGTH;
    }
}
