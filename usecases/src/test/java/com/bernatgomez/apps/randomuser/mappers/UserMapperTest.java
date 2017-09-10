package com.bernatgomez.apps.randomuser.mappers;

import com.bernatgomez.apps.randomuser.models.DataId;
import com.bernatgomez.apps.randomuser.models.DataLocation;
import com.bernatgomez.apps.randomuser.models.DataLogin;
import com.bernatgomez.apps.randomuser.models.DataName;
import com.bernatgomez.apps.randomuser.models.DataPicture;
import com.bernatgomez.apps.randomuser.models.DataUser;
import com.bernatgomez.apps.randomuser.models.NameModel;
import com.bernatgomez.apps.randomuser.models.UserModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test user mapping class
 *
 * Created by bernatgomez on 10/09/2017.
 */
public class UserMapperTest {

    private static final String TEST_GENDER = "male";
    private static final String TEST_PREFIX_NAME = "Sr.";
    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Dough";
    private static final String TEST_MAIL = "john.dough@gmail.com";

    private DataUser input;
    private UserModel expected;

    @Before
    public void setUp() {
        this.input = this.createDataUserInput();
        this.expected = this.createUserModelOutput();
    }

    @Test
    public void testMapUser() {
        //XXX: fixture already done

        UserModel result = UserMapper.mapUser(input);

        Assert.assertEquals(this.expected, result);
        Assert.assertTrue(this.expected.equals(result));
        Assert.assertFalse(result.getDisabled());
    }

    private UserModel createUserModelOutput() {
        UserModel output = new UserModel();

        output.setName(new NameModel(TEST_FIRST_NAME, TEST_LAST_NAME));
        output.setEmail(TEST_MAIL);
        output.setDisabled(false);
        output.setGender(TEST_GENDER);

        return output;
    }

    private DataUser createDataUserInput() {
        return
            new DataUser(TEST_GENDER, new DataName(TEST_PREFIX_NAME, TEST_FIRST_NAME, TEST_LAST_NAME), new DataLocation(), TEST_MAIL, new DataLogin(), "", "", "", "", new DataId(), new DataPicture(), "");
    }


}
