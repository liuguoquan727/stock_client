package com.michaelliu.kotlin.module.me.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.michaelliu.kotlin.db.entity.User;

import java.util.List;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2018/1/19 09:37.
 */
public class UserViewModel extends ViewModel {

    public LiveData<List<User>> getUsers() {
        return null;
    }
}
