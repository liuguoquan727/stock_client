package com.michaelliu.kotlin.module.repository

import android.os.Bundle
import android.view.View
import com.mdroid.app.TranslucentStatusCompat
import com.mdroid.lib.core.base.Status
import com.mdroid.lib.core.base.Status.STATUS_NORMAL
import com.mdroid.lib.core.utils.UIUtil
import com.michaelliu.kotlin.R
import com.michaelliu.kotlin.base.AppBaseFragment
import com.michaelliu.kotlin.base.AppBaseFragmentPresenter
import com.michaelliu.kotlin.base.AppBaseView
import com.michaelliu.kotlin.utils.CommonUtils
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/6 10:34.
 */
class RepositoryFragment : AppBaseFragment<AppBaseView<Any>, AppBaseFragmentPresenter<AppBaseView<Any>>>() {

    override fun getContentView(): Int {
        return R.layout.fragment_repository
    }

    override fun getPageTitle(): String {
        return "列表"
    }

    override fun getCurrentStatus(): Status {
        return STATUS_NORMAL
    }

    override fun initPresenter(): AppBaseFragmentPresenter<AppBaseView<Any>>? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(parent: View?) {
        TranslucentStatusCompat.requestTranslucentStatus(activity)
        statusBar.setBackgroundResource(R.color.main_color_normal)
        toolBar.setBackgroundResource(R.color.main_color_normal)
        toolBarShadow.visibility = View.GONE
        var textVew = UIUtil.setCenterTitle(toolBar, pageTitle)
        CommonUtils.updateTitleText(textVew)
        val titles = Arrays.asList(*resources.getStringArray(R.array.category))
    }


}