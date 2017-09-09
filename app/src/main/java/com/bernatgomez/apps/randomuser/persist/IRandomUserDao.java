package com.bernatgomez.apps.randomuser.persist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


/**
 * Interface containing posible operations executed in the RandomUser repository
 *
 * Created by bernatgomez on 09/09/2017.
 */
@Dao
public interface IRandomUserDao {

    @Query("SELECT * FROM User")
    public List<User> getUsers();

    @Insert
    public void insertUser(User user);
}
