package com.dss886.dotaautochess.feature.hero.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.widget.TitleView

/**
 * Created by dss886 on 2019/1/25.
 */
class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTitleView: TitleView = itemView as TitleView

    fun bind(level: String) {
        mTitleView.setText(level)
    }

}
