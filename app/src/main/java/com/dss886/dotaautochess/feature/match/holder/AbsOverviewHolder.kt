package com.dss886.dotaautochess.feature.match.holder

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.utils.Logger
import com.dss886.dotaautochess.utils.UIUtils
import com.dss886.dotaautochess.widget.TitleView
import java.util.*

/**
 * Created by dss886 on 2019/1/28.
 *
 * R.layout.match_item_overview
 */
abstract class AbsOverviewHolder<BuffHolder> internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTitleView: TitleView = itemView.findViewById(R.id.title_view)
    private val mNameList = ArrayList<TextView>().apply {
        val itemIds = intArrayOf(R.id.item1, R.id.item2, R.id.item3, R.id.item4,
                R.id.item5, R.id.item6, R.id.item7, R.id.item8, R.id.item9, R.id.item10)
        for (id in itemIds) {
            add(itemView.findViewById<View>(id) as TextView)
        }
    }

    internal abstract fun isBuffEnable(buffHolder: BuffHolder, count: Int): Boolean

    internal abstract fun getItemName(buffHolder: BuffHolder): String

    internal abstract fun getItemColorRes(buffHolder: BuffHolder): Int

    open fun bind(titleRes: Int, dataList: List<Pair<BuffHolder, Int>>) {
        val context = mTitleView.context
        mTitleView.setText(context.getString(titleRes))
        for (i in mNameList.indices) {
            val nameTv = mNameList[i]
            if (i < dataList.size) {
                val pair = dataList[i]
                nameTv.visibility = View.VISIBLE
                bindItem(context, nameTv, pair.first, pair.second)
            } else {
                nameTv.visibility = View.GONE
            }
        }
    }

    private fun bindItem(context: Context?, textView: TextView?, buffHolder: BuffHolder?, count: Int) {
        if (context == null || textView == null || buffHolder == null) {
            return
        }
        if (count == 1) {
            textView.text = getItemName(buffHolder)
        } else {
            textView.text = context.getString(R.string.match_species_and_profession_count, getItemName(buffHolder), count)
        }
        (textView.background as GradientDrawable).let { drawable ->
            if (isBuffEnable(buffHolder, count)) {
                val color = ContextCompat.getColor(context, getItemColorRes(buffHolder))
                // avoid color of the text and background is too close
                val colorBrightness = UIUtils.getColorBrightness(color)
                val textColor = ContextCompat.getColor(context,
                        if (colorBrightness > 130) R.color.black_bb else R.color.white_bb)
                Logger.d("AbsOverviewHolder", getItemName(buffHolder) + colorBrightness)
                drawable.setColor(color)
                textView.setTextColor(textColor)
                textView.alpha = 0.7f
            } else {
                textView.alpha = 1f
                textView.setTextColor(ContextCompat.getColor(context, R.color.white_66))
                drawable.setColor(ContextCompat.getColor(context, R.color.white_0a))
            }
            textView.background.invalidateSelf()
        }
    }

}
