package com.dss886.dotaautochess.feature.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.feature.match.manager.MatchChangeListener
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import com.dss886.dotaautochess.feature.match.pool.HeroPoolView
import com.dss886.dotaautochess.utils.UIUtils

/**
 * Created by dss886 on 2019/1/25.
 */
class MatchFragment : Fragment(), MatchChangeListener {

    private var mRecyclerView: RecyclerView? = null
    private var mMatchAdapter: MatchAdapter? = null
    private var mHeroPoolView: HeroPoolView? = null
    private var mEmptyView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        mHeroPoolView = view.findViewById(R.id.hero_pool)
        mEmptyView = view.findViewById(R.id.empty_view)
        mRecyclerView = view.findViewById(R.id.recycler_view)
        mRecyclerView?.layoutManager = LinearLayoutManager(view.context)
        mMatchAdapter = MatchAdapter()
        mRecyclerView?.adapter = mMatchAdapter

        MatchManager.registerChangeListener(this)
        onHeroesChanged()

        return view
    }

    override fun onDestroyView() {
        MatchManager.unregisterChangeListener(this)
        super.onDestroyView()
    }

    override fun onHeroesChanged() {
        val heroList = MatchManager.heroList
        mHeroPoolView?.bind(heroList)
        mMatchAdapter?.updateData()
        if (heroList.isEmpty()) {
            UIUtils.setVisibility(mEmptyView, View.VISIBLE)
            UIUtils.setVisibility(mRecyclerView, View.GONE)
        } else {
            UIUtils.setVisibility(mEmptyView, View.GONE)
            UIUtils.setVisibility(mRecyclerView, View.VISIBLE)
        }
    }

}
