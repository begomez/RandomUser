package com.bernatgomez.apps.randomuser;


import android.support.test.runner.AndroidJUnit4;

import com.bernatgomez.apps.randomuser.dependencies.components.DaggerAppComponent;
import com.bernatgomez.apps.randomuser.dependencies.modules.AppModule;
import com.bernatgomez.apps.randomuser.forms.FormGetUsers;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.sources.FakeDataSource;
import com.bernatgomez.apps.randomuser.sources.interfaces.IDataSource;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ListPresenterTest {

    private static final String TAG = ListPresenterTest.class.getSimpleName();

    @Mock
    protected IMVPListView view;

    @Inject
    protected Bus bus;

    protected IDataSource fake;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.bus = DaggerAppComponent.builder().appModule(new AppModule()).build().getBus();

        this.fake = new FakeDataSource(this.bus);
    }

    @Test
    public void testGetUsers() throws Exception {
        AndroidLogger.logMsg(TAG, this.bus.toString());

    }
}
