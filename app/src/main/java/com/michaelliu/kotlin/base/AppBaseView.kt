package com.michaelliu.kotlin.base

import com.mdroid.lib.core.base.BaseView

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/3 17:13.
 */
interface AppBaseView<T> : BaseView<T> {
    override fun setLoadingIndicator(isActive: Boolean) {
    }
}