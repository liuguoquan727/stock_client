package com.michaelliu.kotlin.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.michaelliu.kotlin.db.dao.UserDao;
import com.michaelliu.kotlin.db.entity.User;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2017/11/9 11:57.
 */
@Database(
        entities = {User.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "user";

    public abstract UserDao userDao();
}
