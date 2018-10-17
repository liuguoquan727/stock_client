package com.michaelliu.kotlin.module.repository.content

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mdroid.lib.core.base.Status
import com.mdroid.lib.core.base.Status.STATUS_NORMAL
import com.mdroid.lib.core.utils.Toost
import com.mdroid.lib.recyclerview.BaseRecyclerViewAdapter.OnLoadingMoreListener
import com.mdroid.lib.recyclerview.BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener
import com.mdroid.view.recyclerView.flexibledivider.DrawableDivider
import com.michaelliu.kotlin.R
import com.michaelliu.kotlin.base.LazyLoadFragment
import com.michaelliu.kotlin.model.RepositoryModel
import com.michaelliu.kotlin.model.RepositoryModel.RepositoryItem
import com.michaelliu.kotlin.module.repository.content.RepositoryContract.IRepositoryPresenter
import com.michaelliu.kotlin.module.repository.content.RepositoryContract.IRepositoryView
import com.michaelliu.kotlin.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_content_repository.*

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/6 11:30.
 */
abstract class BaseContentFragment : LazyLoadFragment<IRepositoryView, IRepositoryPresenter>(),
        IRepositoryView,
        OnLoadingMoreListener,
        OnRecyclerViewItemClickListener {

    private lateinit var mAdapter: RepositoryContentAdapter
    private var mIsDataLoaded: Boolean = false
    private var mCondition: RepositoryCondition = RepositoryCondition()
    private var mItems: ArrayList<RepositoryModel.RepositoryItem> = ArrayList()

    override fun getPageTitle(): String {
        return ""
    }

    override fun getContentView(): Int {
        return R.layout.fragment_content_repository
    }

    abstract fun getType(): String

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun initPresenter(): IRepositoryPresenter {
        return RepositoryPresenter(mLifecycleProvider, handler)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(parent: View?) {
        super.initView(parent)
        refresh_layout.setColorSchemeResources(R.color.main_color_normal)
        refresh_layout.setOnRefreshListener {
            refresh()
        }
        mAdapter = RepositoryContentAdapter(mItems)
        mAdapter.setOnRecyclerViewItemClickListener(this)
        mAdapter.setHasMore(mItems.size >= 10)
        mAdapter.setOnLoadingMoreListener(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.addItemDecoration(DrawableDivider(mAdapter))
        recyclerview.adapter = mAdapter
        CommonUtils.setRecyclerViewLoadMore(activity, mAdapter, recyclerview)

    }

    override fun onItemClick(
            view: View?,
            position: Int
    ) {
        var intent = Intent()
        intent.action = "android.intent.action.VIEW"
        var uri: Uri = Uri.parse(mItems[position].html_url)
        intent.data = uri
        startActivity(intent)
    }

    override fun lazyInitView(parent: View?) {
        if (!mIsDataLoaded) {
            mIsDataLoaded = true
            refresh()
        }

    }

    private fun refresh() {
        mCondition.q = getType()
        mCondition.sort = "stars"
        mCondition.order = "desc"
        mCondition.page = 1
        mCondition.limit = 10
        mPresenter.query(mCondition)
    }

    override fun requestMoreData() {
        mCondition.page++
        mPresenter.query(mCondition)
    }

    override fun showSuccess(items: List<RepositoryItem>) {
        switchStatus(STATUS_NORMAL)
        stopRefresh()
        if (mAdapter.isLoadingMore) {
            mAdapter.loadMoreFinish(items.size == mCondition.limit, items)
        } else {
            mAdapter.setHasMore(items.size == mCondition.limit)
            mAdapter.resetData(items)
        }
    }

    override fun showError(error: String) {
        Toost.message(error)
        stopRefresh()
        if (mAdapter.isLoadingMore) {
            switchStatus(Status.STATUS_NORMAL)
            mAdapter.loadMoreFailure()
        } else {
            if (mItems.size > 0) {
                switchStatus(Status.STATUS_NORMAL)
            } else {
                switchStatus(Status.STATUS_ERROR)
                mErrorView.setOnClickListener { refresh() }
            }
        }
    }

    private fun stopRefresh() {
        if (refresh_layout != null && refresh_layout.isRefreshing) {
            refresh_layout.isRefreshing = false
        }
    }
}