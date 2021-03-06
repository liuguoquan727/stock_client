package com.michaelliu.kotlin.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mdroid.lib.core.base.BaseActivity;
import com.mdroid.lib.core.base.BaseView;
import com.mdroid.lib.core.eventbus.EventBusEvent;
import com.mdroid.lib.core.utils.UIUtil;
import com.michaelliu.kotlin.R;
import com.michaelliu.kotlin.utils.ToolBarUtils;

/**
 * Description：
 */
public abstract class AppBaseActivity<V extends AppBaseView, T extends AppBaseActivityPresenter<V>>
        extends BaseActivity<V, T> implements BaseView<T>, EventBusEvent.INotify {

    private Unbinder mUnbinder;

    @Override
    protected void bind() {
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void unbind() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    /**
     * 数据等加载指示器，默认空实现
     *
     * @param isActive 是否正在处理
     */
    @Override
    public void setLoadingIndicator(boolean isActive) {}

    @Override
    public void onNotify(EventBusEvent event) {}

    protected void requestBaseInit(Toolbar toolBar, String title) {
        toolBar.setBackgroundResource(R.color.main_color_normal);
        TextView tvTitle = UIUtil.setCenterTitle(toolBar, title);
        tvTitle.setBackgroundResource(R.color.main_color_normal);
        ToolBarUtils.updateTitleText(tvTitle);
        toolBar.setNavigationIcon(R.drawable.ic_back);
        toolBar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
