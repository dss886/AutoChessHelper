package com.dss886.dotaautochess.feature.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.IBuffHolder
import com.dss886.dotaautochess.feature.match.manager.MatchChangeListener
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import java.text.Collator
import java.util.*

/**
 * Created by dss886 on 2019/1/29.
 */
class FilterFragment : Fragment(), MatchChangeListener {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: FilterAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view)
        mRecyclerView?.layoutManager = LinearLayoutManager(view.context)
        mRecyclerView?.adapter = FilterAdapter(view.context).apply { mAdapter = this }
        MatchManager.registerChangeListener(this)
        return view
    }

    override fun onDestroyView() {
        MatchManager.unregisterChangeListener(this)
        super.onDestroyView()
    }

    override fun onHeroesChanged() {
        mAdapter?.notifyDataSetChanged()
    }

    private fun <T : IBuffHolder> sortList(list: List<T>): List<T> {
        return list.sortedWith(kotlin.Comparator { o1, o2 ->
            if (o1.desc.length != o2.desc.length) {
                Integer.compare(o1.desc.length, o2.desc.length)
            } else {
                Collator.getInstance(Locale.CHINESE).compare(o1.desc, o2.desc)
            }
        })
    }

}
