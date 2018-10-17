package com.michaelliu.kotlin.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mdroid.app.TranslucentStatusCompat;
import com.mdroid.lib.core.base.BaseFragment;
import com.mdroid.lib.core.eventbus.EventBusEvent;
import com.mdroid.lib.core.utils.UIUtil;
import com.michaelliu.kotlin.R;
import com.michaelliu.kotlin.utils.ToolBarUtils;

/**
 * Description：
 */
public abstract class AppBaseFragment<V extends AppBaseView, T extends AppBaseFragmentPresenter<V>>
    extends BaseFragment<V, T> implements EventBusEvent.INotify {

  private Unbinder mUnbinder;

  protected View initErrorView() {
    View errorView = mInflater.inflate(R.layout.view_error, mContentContainer, false);
    setErrorView(errorView);
    return errorView;
  }

  @Override
  protected void bind(View view) {
    mUnbinder = ButterKnife.bind(view);
  }

  @Override
  protected void unbind() {
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  protected void showProcessDialog() {
  }

  protected void dismissProcessDialog() {
  }

  protected void requestBaseInit(String title, boolean isBack) {
    TranslucentStatusCompat.requestTranslucentStatus(getActivity());
    getToolBarShadow().setVisibility(View.GONE);
    Toolbar toolBar = getToolBar();
    getStatusBar().setBackgroundResource(R.color.main_color_normal);
    toolBar.setBackgroundResource(R.color.main_color_normal);
    TextView tvTitle = UIUtil.setCenterTitle(toolBar, title);
    ToolBarUtils.updateTitleText(tvTitle);
    if (isBack) {
      toolBar.setNavigationIcon(R.drawable.ic_back);
      toolBar.setNavigationOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getActivity().onBackPressed();
            }
          });
    }
  }

  @Override
  public void onNotify(EventBusEvent event) {}
}
