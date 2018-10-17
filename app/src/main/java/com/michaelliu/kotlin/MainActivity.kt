package com.michaelliu.kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.view.MenuItem
import android.widget.Toast
import com.mdroid.app.TabManager
import com.mdroid.lib.core.base.Status
import com.mdroid.lib.core.base.Status.STATUS_NORMAL
import com.michaelliu.kotlin.base.AppBaseActivity
import com.michaelliu.kotlin.base.AppBaseActivityPresenter
import com.michaelliu.kotlin.base.AppBaseView
import com.michaelliu.kotlin.module.me.MeFragment
import com.michaelliu.kotlin.module.repository.RepositoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppBaseActivity<AppBaseView<Any>, AppBaseActivityPresenter<AppBaseView<Any>>>(), OnNavigationItemSelectedListener {

    val REPOSITORY = "列表"
    val USER = "大盘"
    val MORE = "我的"
    lateinit var mTabManager: TabManager

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                if (REPOSITORY != mTabManager.getCurrentTag()) {
                    mTabManager.changeTab(REPOSITORY)
                }
                return true
            }
            R.id.navigation_dashboard -> {
                if (USER != mTabManager.getCurrentTag()) {
                    mTabManager.changeTab(USER)
                }
                return true
            }
            R.id.navigation_notifications -> {
                if (MORE != mTabManager.getCurrentTag()) {
                    mTabManager.changeTab(MORE)
                }
                return true
            }
        }
        return false
    }

    override fun initPresenter(): AppBaseActivityPresenter<AppBaseView<Any>>? {
        return null
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun getPageTitle(): String {
        return ""
    }

    override fun getCurrentStatus(): Status {
        return STATUS_NORMAL
    }

    override fun initData(savedInstanceState: Bundle?) {
        navigation.setOnNavigationItemSelectedListener(this)
        mTabManager = TabManager(this, supportFragmentManager, R.id.main_container)
        mTabManager.addTab(REPOSITORY, RepositoryFragment::class.java, null)
                .addTab(USER, MeFragment::class.java, null)
                .addTab(MORE, MeFragment::class.java, null)
        if (savedInstanceState != null) {
            mTabManager.restoreState(savedInstanceState)
        } else {
            mTabManager.changeTab(REPOSITORY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mTabManager.onSaveInstanceState(outState)
    }

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }
}
