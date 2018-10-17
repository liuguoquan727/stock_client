package com.michaelliu.kotlin.module.me.room;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.rxjava.PausedHandlerScheduler;
import com.mdroid.lib.core.utils.Toost;
import com.michaelliu.kotlin.R;
import com.michaelliu.kotlin.base.AppBaseFragment;
import com.michaelliu.kotlin.db.DBRoom;
import com.michaelliu.kotlin.db.entity.Address;
import com.michaelliu.kotlin.db.entity.User;
import com.orhanobut.logger.Logger;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2017/11/9 16:32.
 */
public class RoomUI extends AppBaseFragment {

    @Override
    protected Status getCurrentStatus() {
        return null;
    }

    @Override
    protected int getContentView() {
        return R.layout.module_room_ui;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected String getPageTitle() {
        return "Room 框架";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initView(View parent) {
        requestBaseInit(getPageTitle(), true);
    }

    @OnClick({R.id.create, R.id.insert, R.id.query, R.id.delete, R.id.update})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                DBRoom.getInstance();
                Logger.d("create");
                break;
            case R.id.insert:
                doInsert();
                break;
            case R.id.query:
                doQueryAll();
                break;
            case R.id.delete:
                doDelete();
                break;
            case R.id.update:
                doUpdate();
                break;
        }
    }

    private void doUpdate() {
        DBRoom.getInstance()
                .getUserDao()
                .getObservableUserById("111")
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                Logger.d(user);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                            }
                        });
    }

    private void doDelete() {
        DBRoom.getInstance()
                .getUserDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<List<User>>() {
                            @Override
                            public void accept(List<User> users) throws Exception {
                                DBRoom.getInstance().getUserDao().delete(users.get(0));
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Logger.d(throwable.getMessage());
                            }
                        });
    }

    private void doQueryAll() {
        DBRoom.getInstance()
                .getUserDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(PausedHandlerScheduler.from(getHandler()))
                .subscribe(
                        new Consumer<List<User>>() {
                            @Override
                            public void accept(List<User> users) throws Exception {
                                Logger.d(users);
                                Toost.message(users.toString());
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Logger.d(throwable.getMessage());
                            }
                        });
    }

    private void doInsert() {
        Observable.create(
                new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> e) throws Exception {
                        User user = new User();
                        user.userId = "111";
                        user.name = "liu";
                        user.age = 20;
                        Address address = new Address();
                        address.city = "shenzhen";
                        user.address = address;
                        try {
                            User user1 =
                                    DBRoom.getInstance()
                                            .getUserDao()
                                            .getUserById(user.userId);
                            if (user1 == null) {
                                long id =
                                        DBRoom.getInstance().getUserDao().insertUser(user);
                                e.onNext(id);
                                e.onComplete();
                            } else {
                                e.onError(new Exception("重复的 userId"));
                            }
                        } catch (Exception e1) {
                            e.onError(e1);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(PausedHandlerScheduler.from(getHandler()))
                .subscribe(
                        new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Logger.d(aLong);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                toastMsg(throwable.getMessage());
                            }
                        });
    }
}
