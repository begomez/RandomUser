package com.bernatgomez.apps.randomuser.mappers;


import com.bernatgomez.apps.randomuser.models.DataUser;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Mapper to convert api users into app users
 *
 * Created by bernatgomez on 09/09/2017.
 */
public abstract class UserMapper {

    /**
     * Map list of user from API to APP model
     * @param apiUsers
     * @return
     */
    public static List<UserModel> mapUsers(List<DataUser> apiUsers) {
        List<UserModel> appUsers = new ArrayList<>();

        for (DataUser user : apiUsers) {
            appUsers.add(UserMapper.mapUser(user));
        }

        return appUsers;
    }

    /**
     * Map individual user from API to APP model
     *
     * @param apiUser
     * @return
     */
    public static UserModel mapUser(DataUser apiUser) {
        return new ModelMapper().map(apiUser, UserModel.class);

    }
}
