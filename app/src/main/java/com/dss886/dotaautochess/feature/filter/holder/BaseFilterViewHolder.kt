package com.dss886.dotaautochess.feature.filter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import com.dss886.dotaautochess.utils.UIUtils

/**
 * Created by dss886 on 2019/1/25.
 */
abstract class BaseFilterViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val mDesc: TextView = itemView.findViewById(R.id.desc)
    private val mHeroList: TextView = itemView.findViewById(R.id.heros)
    private val mCount: TextView = itemView.findViewById(R.id.count)
    private val mDivider: View = itemView.findViewById(R.id.divider)

    protected abstract fun getHeroList(data: T): List<Hero>

    open fun bind(data: T, showDivider: Boolean) {
        val heroList = getHeroList(data)
        mHeroList.text = heroList.joinToString(", ") { it.desc }
        val hasCount = heroList.count { MatchManager.containHero(it) }
        if (hasCount > 0) {
            mCount.text = itemView.context.getString(R.string.filter_item_count, hasCount, heroList.size)
        } else {
            mCount.text = null
        }
        UIUtils.setVisibility(mDivider, if (showDivider) View.VISIBLE else View.GONE)
    }

}
