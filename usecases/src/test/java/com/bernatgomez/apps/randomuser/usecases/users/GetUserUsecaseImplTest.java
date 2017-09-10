package com.bernatgomez.apps.randomuser.usecases.users;


import com.bernatgomez.apps.randomuser.sources.FakeDataSource;
import com.bernatgomez.apps.randomuser.sources.interfaces.IDataSource;
import com.bernatgomez.apps.randomuser.usecases.core.IBaseUsecase;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for get users usecase implementation
 *
 * Created by bernatgomez on 10/09/2017.
 */
public class GetUserUsecaseImplTest {
    protected Bus bus;
    protected IDataSource fake;
    protected IBaseUsecase usecase;


    @Before
    public void setUp() {
        this.createComponents();
        this.createClassUnderTest();
    }

    private void createComponents() {
        this.bus = new Bus(ThreadEnforcer.ANY);
        this.fake = new FakeDataSource(this.bus);
    }

    private void createClassUnderTest() {
        this.usecase = new GetUsersUsecaseImpl(this.bus, this.fake);
    }

    @Test
    public void testRegisterInBus() {
        this.usecase.registerInBus();

        Assert.assertTrue(this.usecase.isRegistered());
    }

    @Test
    public void testUnregisterFromBus() {
        this.usecase.unregisterFromBus();

        Assert.assertFalse(this.usecase.isRegistered());
    }

    @Test
    public void testPerformAction() {
        //TODO:
    }
}
