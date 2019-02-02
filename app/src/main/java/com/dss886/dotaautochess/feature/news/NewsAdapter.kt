package com.dss886.dotaautochess.feature.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.network.data.Feed

/**
 * Created by dss886 on 2019/2/1.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_POST = 1000
        private const val TYPE_FOOTER = 1001
    }

    private var mHasMore = true
    private var mFeedList = mutableListOf<Feed>()

    fun updateData(isLoadMore : Boolean, feedList: List<Feed>, hasMore: Boolean) {
        mHasMore = hasMore
        if (isLoadMore) {
            val nowCount = mFeedList.size
            mFeedList.addAll(feedList)
            notifyItemRangeInserted(nowCount, mFeedList.size - 1)
        } else {
            mFeedList.clear()
            mFeedList.addAll(feedList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_POST -> NewsViewHolder(inflater.inflate(R.layout.news_list_item, parent, false))
            else -> NewsFooterHolder(inflater.inflate(R.layout.news_list_item_footer, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (!mHasMore && position == mFeedList.size) {
            return TYPE_FOOTER
        }
        return TYPE_POST
    }

    override fun getItemCount(): Int {
        return if (mHasMore) mFeedList.size else mFeedList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder){
            holder.bind(mFeedList[position])
        }
    }

}