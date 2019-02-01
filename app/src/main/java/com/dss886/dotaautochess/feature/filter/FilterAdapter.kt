package com.dss886.dotaautochess.feature.filter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Price
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.feature.filter.holder.FilterPriceViewHolder
import com.dss886.dotaautochess.feature.filter.holder.FilterProfessionViewHolder
import com.dss886.dotaautochess.feature.filter.holder.FilterSpeciesViewHolder
import com.dss886.dotaautochess.feature.hero.holder.TitleHolder
import com.dss886.dotaautochess.utils.DataWrapper
import java.util.*

/**
 * Created by dss886 on 2019/1/25.
 */
class FilterAdapter (val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 1000
        private const val TYPE_PRICE = 1001
        private const val TYPE_SPECIES = 1002
        private const val TYPE_PROFESSION = 1003
    }

    private var mRecyclerView: RecyclerView? = null
    private val mDataList = ArrayList<DataWrapper>()

    init {
        mDataList.add(DataWrapper(TYPE_HEADER, context.getString(R.string.filter_price)))
        Price.values().forEach {
            mDataList.add(DataWrapper(TYPE_PRICE, it))
        }
        mDataList.add(DataWrapper(TYPE_HEADER, context.getString(R.string.filter_species)))
        Species.values().forEach {
            mDataList.add(DataWrapper(TYPE_SPECIES, it))
        }
        mDataList.add(DataWrapper(TYPE_HEADER, context.getString(R.string.filter_profession)))
        Profession.values().forEach {
            mDataList.add(DataWrapper(TYPE_PROFESSION, it))
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> TitleHolder(inflater.inflate(R.layout.hero_item_header_big, parent, false))
            TYPE_PRICE -> FilterPriceViewHolder(inflater.inflate(R.layout.filter_item, parent, false))
            TYPE_SPECIES -> FilterSpeciesViewHolder(inflater.inflate(R.layout.filter_item, parent, false))
            else -> FilterProfessionViewHolder(inflater.inflate(R.layout.filter_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        when (holder) {
            is TitleHolder -> holder.bind(data.get())
            is FilterPriceViewHolder -> holder.bind(data.get(), showDivider(position))
            is FilterSpeciesViewHolder -> holder.bind(data.get(), showDivider(position))
            is FilterProfessionViewHolder -> holder.bind(data.get(), showDivider(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].type
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    private fun showDivider(position: Int): Boolean {
        if (position >= mDataList.size - 1) {
            return false
        } else if (mDataList[position + 1].type == TYPE_HEADER) {
            return false
        }
        return true
    }

}
