package com.dss886.dotaautochess.feature.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.IBuffHolder
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.feature.match.holder.BuffContentHolder
import com.dss886.dotaautochess.feature.match.holder.BuffTitleHolder
import com.dss886.dotaautochess.feature.match.holder.ProfessionOverviewHolder
import com.dss886.dotaautochess.feature.match.holder.SpeciesOverviewHolder
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import com.dss886.dotaautochess.utils.BuffUtils

/**
 * Created by dss886 on 2019/1/25.
 */
internal class MatchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SPECIES = 1000
        private const val TYPE_PROFESSION = 1001
        private const val TYPE_BUFF_TITLE = 1002
        private const val TYPE_BUFF_CONTENT = 1003
    }

    private var mSpeciesList = listOf<Pair<Species, Int>>()
    private var mProfessionList = listOf<Pair<Profession, Int>>()
    private var mMergedList = mutableListOf<Pair<IBuffHolder, Int>>()

    init {
        updateData()
    }

    fun updateData() {
        mSpeciesList = MatchManager.speciesList
        mProfessionList = MatchManager.professionList
        val enableList = ArrayList<Pair<IBuffHolder, Int>>()
        val disableList = ArrayList<Pair<IBuffHolder, Int>>()
        for (pair in mSpeciesList) {
            if (BuffUtils.isBuffEnable(pair.first.buffList, pair.second)) {
                enableList.add(pair)
            } else {
                disableList.add(pair)
            }
        }
        for (pair in mProfessionList) {
            if (BuffUtils.isBuffEnable(pair.first.buffList, pair.second)) {
                enableList.add(pair)
            } else {
                disableList.add(pair)
            }
        }
        mMergedList.clear()
        mMergedList.addAll(enableList)
        mMergedList.addAll(disableList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_SPECIES -> SpeciesOverviewHolder(inflater.inflate(R.layout.match_item_overview, parent, false))
            TYPE_PROFESSION -> ProfessionOverviewHolder(inflater.inflate(R.layout.match_item_overview, parent, false))
            TYPE_BUFF_TITLE -> BuffTitleHolder(inflater.inflate(R.layout.match_item_buff_title, parent, false))
            else -> BuffContentHolder(inflater.inflate(R.layout.match_item_buff_content, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SpeciesOverviewHolder -> holder.bind(R.string.match_species_title, mSpeciesList)
            is ProfessionOverviewHolder -> holder.bind(R.string.match_profession_title, mProfessionList)
            is BuffContentHolder -> {
                val pair = mMergedList[position - 3]
                holder.bind(pair.first, pair.second, position == itemCount - 1)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_SPECIES
            1 -> TYPE_PROFESSION
            2 -> TYPE_BUFF_TITLE
            else -> TYPE_BUFF_CONTENT
        }
    }

    override fun getItemCount(): Int {
        return 3 + mSpeciesList.size + mProfessionList.size
    }

}
