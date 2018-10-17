package com.michaelliu.kotlin.module.me.lifycycle;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.michaelliu.kotlin.R;
import com.michaelliu.kotlin.base.AppBaseActivity;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2018/1/18 17:12.
 */
public class AndroidLifeCycleActivity extends AppBaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    protected Status getCurrentStatus() {
        return null;
    }

    @Override
    protected String getPageTitle() {
        return "Lifecyle";
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentView() {
        return R.layout.module_me_lifecycle_ui;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        requestBaseInit(mToolbar, getPageTitle());
        getLifecycle().addObserver(new MyLifecycleObserver());
    }
}
