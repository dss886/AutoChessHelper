package com.dss886.dotaautochess.feature.hero.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/25.
 */
class HeroCountHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTextView: TextView = itemView.findViewById(R.id.text)

    fun bind(count: Int) {
        mTextView.text = mTextView.context.getString(R.string.hero_list_footer, count)
    }

}
