package com.bernatgomez.apps.randomuser.mvp.presenter;


import com.bernatgomez.apps.randomuser.dependencies.components.AppComponent;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerAppComponent;
import com.bernatgomez.apps.randomuser.dependencies.components.DaggerListComponent;
import com.bernatgomez.apps.randomuser.dependencies.modules.AppModule;
import com.bernatgomez.apps.randomuser.dependencies.modules.ListModule;
import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.persist.transactions.DbTransactionExecutor;
import com.bernatgomez.apps.randomuser.persist.transactions.IExecutor;
import com.bernatgomez.apps.randomuser.usecases.core.IBaseUsecase;
import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


/**
 * Instrumentation test for ListPresenter
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListPresenterTest {

    private static final String TAG = ListPresenterTest.class.getSimpleName();

    @Mock
    protected IMVPListView view;


    @Inject
    protected Bus bus;

    @Inject
    IBaseUsecase usecase;

    @Inject
    DbTransactionExecutor exec;

    /**
     * Class instance under test
     */
    protected ListPresenter presenter;


    @Before
    public void setUp() {
        this.injectWithMockito();
        this.injectWithDagger();
        this.createClassUnderTest();
    }

    private void injectWithMockito() {
        MockitoAnnotations.initMocks(this);
    }

    private void injectWithDagger() {
        AppComponent injector = DaggerAppComponent.builder().appModule(new AppModule()).build();

        this.bus = injector.getBus();
        this.usecase = DaggerListComponent.builder().appComponent(injector).listModule(new ListModule()).build().getUsecase();
        this.exec = DaggerListComponent.builder().appComponent(injector).listModule(new ListModule()).build().getExecutor();
    }

    private void createClassUnderTest() {
        this.presenter = new ListPresenter(bus, usecase, exec);

        this.presenter.attachView(this.view);
    }

    @Test
    public void testGetRandomUsers() throws Exception {
        this.presenter.getRandomUsers(true);

        verify(this.view).showLoading();

        verify(this.usecase).performAction();
    }

    @Test
    public void testFilterUsers() {
        String query = "John Dough";

        verify(this.view).getAdapter().filter(query);
        verify(this.view).resetScroll();
    }

    @Test
    public void testDisableUser() {
        UserModel user = new UserModel();

        verify(this.view).disableUser(user);
        verify(this.exec).execute(IExecutor.CmdType.DISABLE_USER, user);
    }
}
