package com.dss886.dotaautochess.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.network.Api
import com.dss886.dotaautochess.network.data.Timeline
import com.dss886.dotaautochess.utils.Constants
import com.dss886.dotaautochess.utils.dpInt
import com.dss886.dotaautochess.utils.toColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by dss886 on 2019/1/25.
 */
class NewsFragment : Fragment() {

    companion object {
        const val PRE_LOAD_COUNT = 5
    }

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: NewsAdapter? = null

    private var mIsLoading = false
    private var mHasMore = true
    private var mLastFeedId = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            mRecyclerView = this
            layoutManager = LinearLayoutManager(view.context)
            adapter = NewsAdapter().apply { mAdapter = this }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    tryToPreLoad(recyclerView)
                }
            })
        }

        view.findViewById<SwipeRefreshLayout>(R.id.refresh).apply {
            mSwipeRefreshLayout = this
            setColorSchemeColors(R.color.color_accent.toColor())
            setProgressBackgroundColorSchemeColor(R.color.color_primary.toColor())
            setProgressViewOffset(false, 0, 32.dpInt)
            setOnRefreshListener { fetchData(true) }
        }

        fetchData(false)
        return view
    }

    private fun tryToPreLoad(recyclerView: RecyclerView) {
        if (!mHasMore) {
            return
        }
        (recyclerView.layoutManager as? LinearLayoutManager)?.let { lm ->
            val first = lm.findFirstVisibleItemPosition()
            val visible = lm.findLastVisibleItemPosition() - first
            val total = lm.itemCount
            // Add a condition of (first > 0),
            // avoiding triggering onScrollBottom() on the initialization
            if (total > 1 && total <= first + visible + PRE_LOAD_COUNT && first > 0) {
                fetchData(true)
            }
        }
    }

    private fun fetchData(isLoadMore : Boolean) {
        if (mIsLoading) {
            return
        }
        mIsLoading = true
        Api.get(Constants.HOST_NEWS + mLastFeedId, success = { response ->
            doAsync {
                val timeline = Timeline()
                timeline.parseData(response.body()?.string())
                uiThread {
                    mIsLoading = false
                    mSwipeRefreshLayout?.isRefreshing = false
                    mHasMore = timeline.feedList.size > 0
                    mLastFeedId = timeline.lastFeedId
                    mAdapter?.updateData(isLoadMore, timeline.feedList, mHasMore)
                }
            }
        }, failure = {
            mSwipeRefreshLayout?.isRefreshing = false
            mIsLoading = false
        })
    }

}
