package com.dss886.dotaautochess.feature.filter.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/30.
 */
class FilterListFragment : Fragment() {

    companion object {
        const val BUNDLE_TYPE = "type"
        const val BUNDLE_VALUE = "value"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        return view
    }

}
