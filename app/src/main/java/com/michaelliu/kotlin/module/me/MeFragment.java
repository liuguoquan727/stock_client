package com.michaelliu.kotlin.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;
import com.michaelliu.kotlin.R;
import com.michaelliu.kotlin.base.AppBaseFragment;
import com.michaelliu.kotlin.module.me.lifycycle.AndroidLifeCycleActivity;
import com.michaelliu.kotlin.module.me.room.RoomUI;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2018/1/18 14:23.
 */
public class MeFragment extends AppBaseFragment {

    @Override
    protected Status getCurrentStatus() {
        return null;
    }

    @Override
    protected int getContentView() {
        return R.layout.module_me_ui;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected String getPageTitle() {
        return "我的";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initView(View parent) {
        requestBaseInit(getPageTitle(), false);
    }

    @OnClick({R.id.room, R.id.lifecycle})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.room:
                ActivityUtil.startActivity(this, RoomUI.class);
                break;
            case R.id.lifecycle:
                Intent intent = new Intent(getActivity(), AndroidLifeCycleActivity.class);
                ActivityUtil.startActivity(this, intent);
                break;
        }
    }
}
