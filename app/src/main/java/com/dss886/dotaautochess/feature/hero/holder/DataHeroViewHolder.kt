package com.dss886.dotaautochess.feature.hero.holder

import android.view.View
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.hero.IHeroItemCallback

/**
 * Created by dss886 on 2019/1/25.
 */
class DataHeroViewHolder(itemView: View, private val callback: IHeroItemCallback? = null)
    : BaseHeroViewHolder(itemView) {

    override fun bind(hero: Hero, position: Int, isExpanded: Boolean) {
        super.bind(hero, position, isExpanded)
        mTopLayout.setOnClickListener { callback?.onItemClick(position, hero) }
    }

}
