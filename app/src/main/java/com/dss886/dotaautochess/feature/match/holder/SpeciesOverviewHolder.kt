package com.dss886.dotaautochess.feature.match.holder

import android.view.View
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.utils.UIUtils
import com.dss886.dotaautochess.utils.dpInt

/**
 * Created by dss886 on 2019/1/27.
 *
 * R.layout.match_item_overview
 */
class SpeciesOverviewHolder(itemView: View) : AbsOverviewHolder<Species>(itemView) {

    private val mTitleView: View = itemView.findViewById(R.id.title_view)

    override fun bind(titleRes: Int, dataList: List<Pair<Species, Int>>) {
        super.bind(titleRes, dataList)
        UIUtils.updateLayoutMargin(mTitleView, UIUtils.KEEP, (-10).dpInt, UIUtils.KEEP, UIUtils.KEEP)
    }

    override fun isBuffEnable(buffHolder: Species, count: Int): Boolean {
        return if (buffHolder.buffList.isEmpty()) {
            false
        } else count >= buffHolder.buffList[0].count
    }

    override fun getItemName(buffHolder: Species): String {
        return buffHolder.desc
    }

    override fun getItemColorRes(buffHolder: Species): Int {
        return buffHolder.colorRes
    }

}
