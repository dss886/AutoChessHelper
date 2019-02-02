package com.dss886.dotaautochess.feature.news

import android.graphics.Outline
import android.text.TextUtils
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.feature.news.detail.NewsDetailActivity
import com.dss886.dotaautochess.network.data.Feed
import com.dss886.dotaautochess.utils.UIUtils
import com.dss886.dotaautochess.utils.loadImage
import com.dss886.dotaautochess.utils.toShortTime

/**
 * Created by dss886 on 2019/2/2.
 *
 * R.layout.news_item
 */
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTitle: TextView = itemView.findViewById(R.id.title)
    private val mContent: TextView = itemView.findViewById(R.id.content)
    private val mImageView: ImageView = itemView.findViewById(R.id.image)
    private val mUserAvatar: ImageView = itemView.findViewById(R.id.user_avatar)
    private val mUserName: TextView = itemView.findViewById(R.id.user_name)
    private val mCreateTime: TextView = itemView.findViewById(R.id.create_time)
    private val mReplyCount: TextView = itemView.findViewById(R.id.reply_count)

    init {
        mUserAvatar.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setOval(0, 0, view.width, view.height)
            }
        }
        mUserAvatar.clipToOutline = true
    }

    fun bind(feed: Feed) {
        mTitle.text = feed.title
        mContent.text = feed.shortContent
        if (TextUtils.isEmpty(feed.coverUrl)) {
            UIUtils.setVisibility(mImageView, View.GONE)
        } else {
            UIUtils.setVisibility(mImageView, View.VISIBLE)
            mImageView.loadImage(feed.coverUrl)
        }
        mUserAvatar.loadImage(feed.userAvatarUrl)
        mUserName.text = feed.userName
        mCreateTime.text = feed.createTime.toShortTime()
        mReplyCount.text = feed.replyCount.toString()
        itemView.setOnClickListener {
            NewsDetailActivity.startActivity(itemView.context, feed.title, feed.id)
        }
    }

}