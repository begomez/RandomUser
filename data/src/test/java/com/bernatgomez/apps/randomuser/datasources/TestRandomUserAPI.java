package com.bernatgomez.apps.randomuser.datasources;


import com.bernatgomez.apps.randomuser.api.IRandomUserAPI;
import com.bernatgomez.apps.randomuser.datasources.interfaces.IDataSource;
import com.bernatgomez.apps.randomuser.forms.FormGetUsers;
import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.http.Query;
import rx.Observable;
import rx.observers.TestSubscriber;


/**
 * Test class for API mechanism
 *
 * Created by bernatgomez on 10/09/2017.
 */
public class TestRandomUserAPI {

    protected IRandomUserAPI api;


    @org.junit.Before
    public void setUp() {
        this.createClassUnderTest();
    }

    private void createClassUnderTest() {
        this.api = new IRandomUserAPI() {
            @Override
            public Observable<DataResponse> getUsers(@Query("results") int results) {
                return null;
            }
        };
    }

    @Test
    public void testApi() {
        //TODO:

        TestSubscriber<DataResponse> subscriber = TestSubscriber.create();

        this.api.getUsers(5).subscribe(subscriber);

        subscriber.assertCompleted();

        subscriber.assertNoErrors();
    }

}
