package com.dss886.dotaautochess.feature.hero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.hero.holder.FooterHolder
import com.dss886.dotaautochess.feature.hero.holder.HeaderHolder
import com.dss886.dotaautochess.feature.hero.holder.HeroViewHolder
import java.util.*

/**
 * Created by dss886 on 2019/1/25.
 */
class HeroAdapter internal constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 1000
        private const val TYPE_FOOTER = 1001
        private const val TYPE_HERO = 1002
    }

    private var mRecyclerView: RecyclerView? = null
    private val mDataList = ArrayList<DataWrapper>()
    private var mCurrentExpandedPosition = -1
    private var mLastExpandCollapseTime = 0L

    private class DataWrapper internal constructor(internal var type: Int, internal var data: Any) {
        internal fun <T> get(): T {
            @Suppress("UNCHECKED_CAST")
            return data as T
        }
    }

    init {
        var currentLevel = 0
        for (hero in Hero.values()) {
            if (hero.price.price > currentLevel) {
                mDataList.add(DataWrapper(TYPE_HEADER, hero.price.description))
                currentLevel++
            }
            mDataList.add(DataWrapper(TYPE_HERO, hero))
        }
        mDataList.add(DataWrapper(TYPE_FOOTER, Hero.values().size))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val view = inflater.inflate(R.layout.hero_item_header, parent, false)
                HeaderHolder(view)
            }
            TYPE_FOOTER -> {
                val view = inflater.inflate(R.layout.hero_item_footer, parent, false)
                FooterHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.hero_item_hero, parent, false)
                HeroViewHolder(this, view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        when (holder) {
            is HeaderHolder -> {
                val level = data.get<String>()
                holder.bind(level)
            }
            is FooterHolder -> {
                val count = data.get<Int>()
                holder.bind(count)
            }
            is HeroViewHolder -> {
                val hero = data.get<Hero>()
                holder.bind(hero, position, mCurrentExpandedPosition == position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].type
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    /**
     * Only allow one item to expand.
     * Method notifyItemChange() has some issues on performance here,
     * so we have to expand it by do our own animations.
     */
    fun onItemExpandToggle(position: Int) {
        if (position < 0 || mRecyclerView == null) {
            return
        }
        val now = System.currentTimeMillis()
        if (now - mLastExpandCollapseTime <= 300L) {
            return
        }
        mLastExpandCollapseTime = now

        val isExpand = mCurrentExpandedPosition != position
        if (isExpand && mCurrentExpandedPosition >= 0) {
            val holder = mRecyclerView!!.findViewHolderForAdapterPosition(mCurrentExpandedPosition)
            if (holder is HeroViewHolder) {
                holder.doExpandOrCollapse(false)
            }
        }
        val holder = mRecyclerView!!.findViewHolderForAdapterPosition(position)
        if (holder is HeroViewHolder) {
            holder.doExpandOrCollapse(isExpand)
        }
        mCurrentExpandedPosition = if (isExpand) position else -1
    }

}
