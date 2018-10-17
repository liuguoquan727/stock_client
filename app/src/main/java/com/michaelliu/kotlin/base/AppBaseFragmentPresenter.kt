package com.michaelliu.kotlin.base

import android.content.Context
import com.mdroid.PausedHandler
import com.mdroid.lib.core.base.BasePresenter
import com.michaelliu.kotlin.App
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/3 17:12.
 */
abstract class AppBaseFragmentPresenter<V>(provider: LifecycleProvider<FragmentEvent>,
                                           handler: PausedHandler) : BasePresenter<V>() {

    protected var mContext: Context = App.Instance()
    protected var mProvider: LifecycleProvider<FragmentEvent> = provider
    protected var mHandler: PausedHandler = handler

    override fun onDestroy() {
    }
}