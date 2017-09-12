package com.bernatgomez.apps.randomuser.mvp.presenter;


import com.bernatgomez.apps.randomuser.models.ErrorModel;
import com.bernatgomez.apps.randomuser.models.NameModel;
import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.mvp.view.IMVPListView;
import com.bernatgomez.apps.randomuser.persist.holder.DiscardedUsersHolder;
import com.bernatgomez.apps.randomuser.persist.transactions.DbTransactionExecutor;
import com.bernatgomez.apps.randomuser.persist.transactions.IExecutor;
import com.bernatgomez.apps.randomuser.usecases.core.IBaseUsecase;
import com.bernatgomez.apps.randomuser.views.adapters.ListAdapter;
import com.squareup.otto.Bus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.verify;


/**
 * Instrumentation test for ListPresenter
 */
public class ListPresenterTest {

    private static final String TAG = ListPresenterTest.class.getSimpleName();

    @Mock
    protected IMVPListView view;

    @Mock
    protected Bus bus;

    @Mock
    protected IBaseUsecase usecase;

    @Mock
    protected DbTransactionExecutor exec;

    @Mock
    protected ListAdapter adapter;

    protected ListPresenter presenter;


    @Before
    public void setUp() {
        this.injectWithMockito();
        this.createClassUnderTest();
    }

    private void injectWithMockito() {
        MockitoAnnotations.initMocks(this);
    }

    private void createClassUnderTest() {
        this.presenter = new ListPresenter(this.bus, this.usecase, this.exec);

        this.presenter.attachView(this.view);
    }

    @Test
    public void testRegisterInBus() {

    // action
        this.presenter.registerInBus();

    // checks
        verify(this.bus).register(this.presenter);

        Assert.assertTrue(this.presenter.isRegistered());
    }

    @Test
    public void testUnregisterFromBus() {

    // action
        this.presenter.unregisterFromBus();

    // checks
        verify(this.bus).unregister(this.presenter);

        Assert.assertFalse(this.presenter.isRegistered());
    }

    @Test
    public void testGetRandomUsers() throws Exception {

        // action
        this.presenter.getRandomUsers(true);

        // checks
        verify(this.view).showLoading();

        verify(this.usecase).performAction();
    }

    @Test
    public void testFilterUsers() {
        String input = "John Dough";

        // action
        this.presenter.filterUsers(input);

        // checks
        verify(this.adapter).filter(input);
        verify(this.view).resetScroll();
    }

    private int getNumOfDiscardedUsers() {
        return DiscardedUsersHolder.getInstance().getDiscardedUsers().size();
    }

    @Test
    public void testDisableUser() {

        // fixture
        int prevLength = this.getNumOfDiscardedUsers();
        UserModel user = new UserModel();

        // action
        this.presenter.disableUser(user);

        // checks
        verify(this.view).disableUser(user);
        verify(this.exec).execute(IExecutor.CmdType.DISABLE_USER, user);
        Assert.assertTrue(prevLength <= DiscardedUsersHolder.getInstance().getDiscardedUsers().size());
        Assert.assertTrue(DiscardedUsersHolder.getInstance().getDiscardedUsers().contains(user));
    }

    private ArrayList<UserModel> getDiscardedList() {
        ArrayList<UserModel> fakeUsers = new ArrayList<UserModel>();

        UserModel fakeUser1 = new UserModel();
        fakeUser1.setName(new NameModel("John", "Dough"));
        fakeUser1.setGender("male");
        fakeUser1.setEmail("john.dough@gmail.com");


        fakeUsers.add(fakeUser1);
        fakeUsers.add(fakeUser1);

        return fakeUsers;
    }

    private ArrayList<UserModel> getApiList() {
        ArrayList<UserModel> fakeUsers = new ArrayList<UserModel>();

        UserModel fakeUser1 = new UserModel();
        fakeUser1.setName(new NameModel("John", "Dough"));
        fakeUser1.setGender("male");
        fakeUser1.setEmail("john.dough@gmail.com");

        UserModel fakeUser2 = new UserModel();
        fakeUser2.setName(new NameModel("Jane", "Dough"));
        fakeUser2.setGender("female");
        fakeUser2.setEmail("jane.dough@gmail.com");

        fakeUsers.add(fakeUser1);
        fakeUsers.add(fakeUser2);

        return fakeUsers;
    }

    @Test
    public void testRemoveDiscardedUsers() {

        // fixture
        ArrayList<UserModel> apiUsers = this.getApiList();
        ArrayList<UserModel> discardedUsers = this.getDiscardedList();

        // action
        this.presenter.removeDiscardedUsers(apiUsers, discardedUsers);

        // checks
        Assert.assertTrue(apiUsers.size() == 1);
        Assert.assertTrue(discardedUsers.size() == 2);
        Assert.assertFalse(apiUsers.contains(discardedUsers.get(0)));
    }

    @Test
    public void testOnSuccess() {

        // fixture
        ArrayList<UserModel> input = new ArrayList<UserModel>();

        // action
        this.presenter.onSuccess(input);

        // checks
        verify(this.view).hideLoading();
        Assert.assertFalse(this.presenter.isRegistered());
    }

    @Test
    public void testOnError() {

        // fixture
        ErrorModel input = new ErrorModel();

        // action
        this.presenter.onError(input);

        // checks
        verify(this.view).hideLoading();
        Assert.assertFalse(this.presenter.isRegistered());
    }
}
