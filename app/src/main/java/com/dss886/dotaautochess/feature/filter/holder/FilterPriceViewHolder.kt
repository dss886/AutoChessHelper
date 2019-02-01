package com.dss886.dotaautochess.feature.filter.holder

import android.view.View
import androidx.core.content.ContextCompat
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.data.Price
import com.dss886.dotaautochess.feature.filter.IFilterController

/**
 * Created by dss886 on 2019/1/25.
 */
open class FilterPriceViewHolder(itemView: View) : BaseFilterViewHolder<Price>(itemView) {

    override fun getHeroList(data: Price): List<Hero> {
        return Hero.values().filter { it.price == data }
    }

    override fun bind(data: Price, showDivider: Boolean) {
        super.bind(data, showDivider)
        mDesc.text = data.desc
        mDesc.setTextColor(ContextCompat.getColor(itemView.context, data.colorRes))
        itemView.setOnClickListener {
            (itemView.context as? IFilterController)?.goDetail(data)
        }
    }


}
