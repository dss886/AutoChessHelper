package com.dss886.dotaautochess.feature.filter.list

import android.view.View
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.hero.IHeroItemCallback
import com.dss886.dotaautochess.feature.hero.holder.BaseHeroViewHolder
import com.dss886.dotaautochess.feature.match.manager.MatchManager

/**
 * Created by dss886 on 2019/1/25.
 */
class FilterHeroViewHolder(itemView: View, private val callback: IHeroItemCallback? = null) : BaseHeroViewHolder(itemView) {

    override fun bind(hero: Hero, position: Int, isExpanded: Boolean) {
        super.bind(hero, position, isExpanded)
        itemView.alpha = if (MatchManager.containHero(hero)) 0.38F else 1F
        itemView.setOnClickListener {
            if (MatchManager.addHero(hero)) {
                callback?.onItemClick(position, hero)
                itemView.animate().alpha(0.3F).setDuration(80L).start()
            }
        }
    }

}
