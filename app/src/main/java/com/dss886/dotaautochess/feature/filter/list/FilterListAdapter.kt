package com.dss886.dotaautochess.feature.filter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.filter.IFilterController
import com.dss886.dotaautochess.feature.hero.IHeroItemCallback
import com.dss886.dotaautochess.feature.hero.holder.BaseHeroViewHolder
import com.dss886.dotaautochess.feature.hero.holder.HeroCountHolder
import java.io.Serializable

/**
 * Created by dss886 on 2019/1/30.
 */
class FilterListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), IHeroItemCallback {

    companion object {
        private const val TYPE_HEADER = 1000
        private const val TYPE_FOOTER = 1001
        const val TYPE_HERO = 1002
    }

    private var mHeroList: List<Hero> = emptyList()
    private var mFilterValue: Serializable? = null

    fun updateData(heroList: List<Hero>, filterValue: Serializable?) {
        mHeroList = heroList
        mFilterValue = filterValue
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> FilterHeaderHolder(inflater.inflate(R.layout.hero_item_header, parent, false))
            TYPE_FOOTER -> HeroCountHolder(inflater.inflate(R.layout.hero_item_footer, parent, false))
            else -> FilterHeroViewHolder(inflater.inflate(R.layout.hero_item_hero, parent, false), this)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            mHeroList.size + 1 -> TYPE_FOOTER
            else -> TYPE_HERO
        }
    }

    override fun getItemCount(): Int {
        return mHeroList.size + 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilterHeaderHolder -> holder.bind(mFilterValue)
            is HeroCountHolder -> holder.bind(mHeroList.size)
            is BaseHeroViewHolder -> holder.bind(mHeroList[position - 1], position, false)
        }
    }

    override fun onItemClick(position: Int, hero: Hero) {
        (context as? IFilterController)?.tryToAddHero()
    }

}