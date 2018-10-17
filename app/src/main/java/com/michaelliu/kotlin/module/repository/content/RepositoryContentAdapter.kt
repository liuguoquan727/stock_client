package com.michaelliu.kotlin.module.repository.content

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.mdroid.lib.recyclerview.BaseRecyclerViewAdapter
import com.mdroid.lib.recyclerview.BaseViewHolder
import com.mdroid.utils.AndroidUtils
import com.mdroid.view.recyclerView.flexibledivider.DrawableDivider.DrawableProvider
import com.michaelliu.kotlin.R
import com.michaelliu.kotlin.model.RepositoryModel
import com.michaelliu.kotlin.model.RepositoryModel.RepositoryItem
import com.michaelliu.kotlin.utils.ImageLoader

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/6 11:43.
 */
class RepositoryContentAdapter(
        data: List<RepositoryModel.RepositoryItem>) : BaseRecyclerViewAdapter<RepositoryModel.RepositoryItem>(
        R.layout.list_item_repository, data), DrawableProvider {
    override fun dividerDrawable(position: Int, parent: RecyclerView?): Drawable? {
        return null
    }

    override fun dividerSize(position: Int, parent: RecyclerView?): Int {
        return AndroidUtils.dp2px(mContext, 10.0f)
    }

    override fun convert(holder: BaseViewHolder?, item: RepositoryItem?) {
        var icon: ImageView? = holder?.getView(R.id.icon)
        ImageLoader.load(icon, R.mipmap.ic_launcher, item?.owner?.avatar_url)
        holder?.setText(R.id.name, item?.name)
        holder?.setText(R.id.user_name, "Author: " + item?.owner?.login)
        holder?.setText(R.id.language, item?.language)
        holder?.setText(R.id.desc, item?.description)
        val stars = "stars:" + item?.stargazers_count + " " + "fork:" + item?.forks_count
        holder?.setText(R.id.stars, stars)
        val time = item?.updated_at?.split("T")
        holder?.setText(R.id.time, "Update at " + time!![0])
    }

}