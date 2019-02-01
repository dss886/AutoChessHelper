package com.dss886.dotaautochess.feature.filter.holder

import android.view.View
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.feature.filter.IFilterController
import com.dss886.dotaautochess.utils.toColor

/**
 * Created by dss886 on 2019/1/25.
 */
open class FilterSpeciesViewHolder(itemView: View) : BaseFilterViewHolder<Species>(itemView) {

    override fun getHeroList(data: Species): List<Hero> {
        return Hero.values().filter { it.speciesList.contains(data) }
    }

    override fun bind(data: Species, showDivider: Boolean) {
        super.bind(data, showDivider)
        mDesc.text = data.desc
        mDesc.setTextColor(data.colorRes.toColor())
        itemView.setOnClickListener {
            (itemView.context as? IFilterController)?.goDetail(data)
        }
    }


}
