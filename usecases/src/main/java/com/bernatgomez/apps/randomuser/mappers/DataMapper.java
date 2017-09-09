package com.bernatgomez.apps.randomuser.mappers;


import com.bernatgomez.apps.randomuser.models.DataUser;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import com.bernatgomez.apps.randomuser.models.UserModel;


/**
 * Mapper to convert user api objects into user app objects
 *
 * Created by bernatgomez on 09/09/2017.
 */
public abstract class DataMapper {

    /**
     * Map list of user from API to APP model
     * @param apiUsers
     * @return
     */
    public static List<UserModel> mapUsers(List<DataUser> apiUsers) {
        List<UserModel> appUsers = new ArrayList<>();

        for (DataUser user : apiUsers) {
            appUsers.add(DataMapper.mapUser(user));
        }

        return appUsers;
    }

    /**
     * Map individual user from API to APP model
     *
     * @param apiUser
     * @return
     */
    private static UserModel mapUser(DataUser apiUser) {
        return new ModelMapper().map(apiUser, UserModel.class);

    }
}
