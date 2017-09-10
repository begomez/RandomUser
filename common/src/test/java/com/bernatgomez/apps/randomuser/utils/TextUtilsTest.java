package com.bernatgomez.apps.randomuser.utils;


import org.junit.Assert;
import org.junit.Test;


/**
 * Test class for text utils
 *
 * Created by bernatgomez on 10/09/2017.
 */
public class TextUtilsTest {

    @Test
    public void testCanBeCapitalized() {
        String capitalizable = new String("capital");
        String expected = new String("Capital");

        String result = TextUtils.capitalize(capitalizable);

        Assert.assertTrue(expected.equals(result));
        Assert.assertEquals(expected, result);

        String notCapitalizable = new String("");
        expected = new String("");

        result = TextUtils.capitalize(notCapitalizable);

        Assert.assertTrue(result.length() == 0);
        Assert.assertTrue(expected.equals(result));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testIsValidAndNotEmptyString() {

        // with content
        String strNotNull = new String("String");

        boolean result = TextUtils.isValidAndNotEmptyString(strNotNull);

        Assert.assertTrue(result);

        // null
        String strNull = null;

        result = TextUtils.isValidAndNotEmptyString(strNull);

        Assert.assertFalse(result);

        // empty
        String strEmpty = new String("");

        result = TextUtils.isValidAndNotEmptyString(strEmpty);

        Assert.assertFalse(result);

        // empty when trimmed
        String strBlank = new String(" ");

        result = TextUtils.isValidAndNotEmptyString(strBlank);

        Assert.assertFalse(result);
    }

    @Test
    public void testCapitalize() {
        String input = new String("lower");
        String expected = new String("Lower");

        String result = TextUtils.capitalize(input);

        Assert.assertEquals(expected, result);
        Assert.assertTrue(expected.equals(result));
    }
}
