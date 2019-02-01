package com.dss886.dotaautochess.feature.filter.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.data.Price
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.utils.Constants
import java.io.Serializable

/**
 * Created by dss886 on 2019/1/30.
 */
class FilterListFragment : Fragment() {

    private var mAdapter: FilterListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.recycledViewPool.setMaxRecycledViews(FilterListAdapter.TYPE_HERO, 15)
        mAdapter = FilterListAdapter(view.context)
        recyclerView.adapter = mAdapter
        updateData()
        return view
    }

    fun updateData() {
        val value = arguments?.getSerializable(Constants.BUNDLE_VALUE)
        mAdapter?.updateData(getData(value), value)
    }

    private fun getData(value: Serializable?): List<Hero> {
        return Hero.values().filter { hero ->
            when(value) {
                is Price -> hero.price == value
                is Species -> hero.speciesList.contains(value)
                is Profession -> hero.profession == value
                else -> false
            }
        }
    }

}
