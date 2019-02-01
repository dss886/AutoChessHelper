package com.dss886.dotaautochess.feature.match.holder

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.IBuffHolder
import com.dss886.dotaautochess.utils.BuffUtils

/**
 * Created by dss886 on 2019/1/27.
 *
 * R.layout.match_item_buff_content
 */
class BuffContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTitle: TextView = itemView.findViewById(R.id.buff_title)
    private val mContent: TextView = itemView.findViewById(R.id.buff_content)
    private val mDivider: View = itemView.findViewById(R.id.divider)

    fun bind(buffHolder: IBuffHolder, count: Int, hideDivider: Boolean) {
        val context = itemView.context
        mTitle.text = buffHolder.buffName
        val description = BuffUtils.getBuffDescription(context, buffHolder)
        if (BuffUtils.isBuffEnable(buffHolder.buffList, count)) {
            mTitle.setTextColor(ContextCompat.getColor(context, buffHolder.colorRes))
            val lines = description.split("\n")
            if (buffHolder.buffList.size == lines.size) {
                val ss = SpannableString(description)
                var start = 0
                for (i in lines.indices) {
                    val buff = buffHolder.buffList[i]
                    if (count >= buff.count) {
                        val color = ContextCompat.getColor(context, R.color.white_88)
                        ss.setSpan(ForegroundColorSpan(color), start, start + lines[i].length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        start += lines[i].length + 1
                    }
                }
                mContent.text = ss
            }
        } else {
            mTitle.setTextColor(ContextCompat.getColor(context, R.color.white_22))
            mContent.text = description
        }
        mDivider.visibility = if (hideDivider) View.GONE else View.VISIBLE
    }

}
