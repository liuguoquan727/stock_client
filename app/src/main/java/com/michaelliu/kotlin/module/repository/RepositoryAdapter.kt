package com.michaelliu.kotlin.module.repository

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.michaelliu.kotlin.module.repository.content.GoFragment
import com.michaelliu.kotlin.module.repository.content.JavaFragment
import com.michaelliu.kotlin.module.repository.content.KotlinFragment
import com.michaelliu.kotlin.module.repository.content.PythonFragment

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/6 11:18.
 */
class RepositoryAdapter(fm: FragmentManager, items: List<String>) : FragmentPagerAdapter(fm) {


    var mItems: List<String> = items

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return JavaFragment()
            }
            1 -> {
                return PythonFragment()
            }
            2 -> {
                return GoFragment()
            }
            3 -> {
                return KotlinFragment()
            }

        }
        return JavaFragment()
    }

    override fun getCount(): Int {
        return mItems.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mItems[position]
    }

}