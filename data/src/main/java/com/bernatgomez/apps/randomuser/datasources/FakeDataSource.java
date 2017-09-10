package com.bernatgomez.apps.randomuser.datasources;

import com.bernatgomez.apps.randomuser.forms.FormGetUsers;
import com.bernatgomez.apps.randomuser.models.DataId;
import com.bernatgomez.apps.randomuser.models.DataLocation;
import com.bernatgomez.apps.randomuser.models.DataLogin;
import com.bernatgomez.apps.randomuser.models.DataName;
import com.bernatgomez.apps.randomuser.models.DataPicture;
import com.bernatgomez.apps.randomuser.models.DataResponse;
import com.bernatgomez.apps.randomuser.models.DataUser;
import com.bernatgomez.apps.randomuser.datasources.interfaces.IDataSource;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;


/**
 * Fake data source for testing purposes
 *
 * Created by bernatgomez on 10/09/2017.
 */
public class FakeDataSource implements IDataSource {

    protected final Bus bus;


    public FakeDataSource(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void getUsers(FormGetUsers form) {
        this.bus.post(this.getFakeUsersResponse());
    }

    private DataResponse getFakeUsersResponse() {
        List<DataUser> users = new ArrayList<DataUser>();

        //XXX: load more data from local JSON
        DataUser user1 = new DataUser("male", new DataName("Sr.", "John", "Dough"), new DataLocation(), "john.dough@gmail.com", new DataLogin(), "", "", "", "", new DataId(), new DataPicture(), "");
        DataUser user2 = new DataUser("female", new DataName("Miss.", "Jane", "Dough"), new DataLocation(), "jane.dough@gmail.com", new DataLogin(), "", "", "", "", new DataId(), new DataPicture(), "");

        users.add(user1);
        users.add(user2);

        DataResponse fake = new DataResponse(users);

        return fake;
    }
}
