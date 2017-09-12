package com.bernatgomez.apps.randomuser.usecases.users;


import com.bernatgomez.apps.randomuser.datasources.FakeDataSource;
import com.bernatgomez.apps.randomuser.datasources.interfaces.IDataSource;
import com.bernatgomez.apps.randomuser.models.DataError;
import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.bernatgomez.apps.randomuser.usecases.core.IBaseUsecase;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Test class for get users usecase implementation
 *
 * Created by bernatgomez on 10/09/2017.
 */
public class GetUserUsecaseImplTest {

    @Mock
    protected Bus bus;

    @Mock
    protected FakeDataSource fake;


    protected GetUsersUsecaseImpl usecase;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.createClassUnderTest();
    }

    private void createClassUnderTest() {
        this.usecase = new GetUsersUsecaseImpl(this.bus, this.fake);
    }

    @Test
    public void testRegisterInBus() {

        // action
        this.usecase.registerInBus();

        // checks
        Assert.assertTrue(this.usecase.isRegistered());
    }

    @Test
    public void testUnregisterFromBus() {

        // action
        this.usecase.unregisterFromBus();

        // checks
        Assert.assertFalse(this.usecase.isRegistered());
    }

    @Test
    public void testOnSuccess() {

        // fixture
        DataResponse input = new DataResponse();

        // action
        this.usecase.onSuccess(input);

        // checks
        Assert.assertFalse(this.usecase.isRegistered());
    }

    @Test
    public void testOnError() {

        // fixture
        DataError input = new DataError();

        // action
        this.usecase.onError(input);

        // checks
        Assert.assertFalse(this.usecase.isRegistered());
    }
}
