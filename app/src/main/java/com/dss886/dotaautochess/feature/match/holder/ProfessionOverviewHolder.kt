package com.dss886.dotaautochess.feature.match.holder

import android.view.View
import com.dss886.dotaautochess.data.Profession

/**
 * Created by dss886 on 2019/1/27.
 *
 * R.layout.match_item_overview
 */
class ProfessionOverviewHolder(itemView: View) : AbsOverviewHolder<Profession>(itemView) {

    override fun isBuffEnable(buffHolder: Profession, count: Int): Boolean {
        return if (buffHolder.buffList.isEmpty()) {
            false
        } else count >= buffHolder.buffList[0].count
    }

    override fun getItemName(buffHolder: Profession): String {
        return buffHolder.desc
    }

    override fun getItemColorRes(buffHolder: Profession): Int {
        return buffHolder.colorRes
    }
}
