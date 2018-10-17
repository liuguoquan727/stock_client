package com.michaelliu.kotlin.db.dao;

import android.arch.persistence.room.*;
import com.michaelliu.kotlin.db.entity.User;
import io.reactivex.Flowable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2017/11/9 11:51.
 */
@Dao
public interface UserDao {

    @Query("select * from user")
    Flowable<List<User>> getAll();

    @Query("select * from user where userId = :userId")
    User getUserById(String userId);

    @Query("select * from user where userId = :userId")
    Flowable<User> getObservableUserById(String userId);

    @Insert(onConflict = REPLACE)
    long insertUser(User user);

    @Update
    int updateUser(User user);

    @Delete
    void delete(User user);
}
