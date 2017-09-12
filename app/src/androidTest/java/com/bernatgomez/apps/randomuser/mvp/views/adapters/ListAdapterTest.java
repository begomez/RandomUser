package com.bernatgomez.apps.randomuser.mvp.views.adapters;


import android.support.v7.util.SortedList;
import android.support.v7.widget.util.SortedListAdapterCallback;

import com.bernatgomez.apps.randomuser.models.NameModel;
import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.views.adapters.ListAdapter;
import com.bernatgomez.apps.randomuser.views.callback.ListComparator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;


/**
 * Instrumentation test for ListAdapter
 *
 * Created by bernatgomez on 12/09/2017.
 */
public class ListAdapterTest {

    @Mock
    protected SortedList<UserModel> sortedData;

    @Mock
    protected SortedListAdapterCallback<UserModel> callback;

    @Mock
    protected ListAdapter.OnItemInteraction listener;

    @Mock
    protected ListComparator comparator;

    protected ListAdapter adapter;

    private ArrayList<UserModel> getAdditionalUserList() {
        ArrayList<UserModel> temp = new ArrayList<UserModel>();


        UserModel fakeUser1 = new UserModel();
        fakeUser1.setName(new NameModel("Jonathan", "Dough"));
        fakeUser1.setGender("male");
        fakeUser1.setEmail("jonathan.dough@gmail.com");

        temp.add(fakeUser1);

        return temp;
    }

    private UserModel getUserFake1() {
        UserModel fakeUser1 = new UserModel();
        fakeUser1.setName(new NameModel("John", "Dough"));
        fakeUser1.setGender("male");
        fakeUser1.setEmail("john.dough@gmail.com");

        return fakeUser1;
    }

    private UserModel getUserFake2() {
        UserModel fakeUser2 = new UserModel();
        fakeUser2.setName(new NameModel("Jane", "Dough"));
        fakeUser2.setGender("female");
        fakeUser2.setEmail("jane.dough@gmail.com");

        return fakeUser2;
    }

    private ArrayList<UserModel> getUserList() {
        ArrayList<UserModel> users = new ArrayList<UserModel>();

        users.add(this.getUserFake1());
        users.add(this.getUserFake2());

        return users;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.createClassUnderTest();
    }

    private void createClassUnderTest() {
        this.adapter = new ListAdapter(getUserList(), this.listener, this.comparator);
    }

    @Test
    public void testAddElements() {
        int prevSize = this.adapter.getItemCount();
        ArrayList<UserModel> moreUsers = this.getAdditionalUserList();
        int expectedSize = this.adapter.getItemCount() + moreUsers.size();

        this.adapter.addElements(moreUsers);
        int result = this.adapter.getItemCount();

        Assert.assertTrue(expectedSize == result);
        Assert.assertTrue(prevSize <= result);
    }

    @Test
    public void testRemoveElement() {
        UserModel target = this.getUserFake1();
        int prevSize = this.adapter.getItemCount();
        int expectedSize = prevSize - 1;

        this.adapter.removeElement(target);
        int result = this.adapter.getItemCount();

        Assert.assertTrue(expectedSize == result);
        Assert.assertTrue(prevSize >= result);
    }
}
