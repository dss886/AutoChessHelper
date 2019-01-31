package com.dss886.dotaautochess.feature.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/25.
 */
class AllHeroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hero, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        // Item caching of RecyclerView may cause reusing item in a wrong way,
        // I haven't found a suitable solution, so set it to zero...
        recyclerView.setItemViewCacheSize(0)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val heroAdapter = AllHeroAdapter()
        recyclerView.adapter = heroAdapter

        return view
    }

}
