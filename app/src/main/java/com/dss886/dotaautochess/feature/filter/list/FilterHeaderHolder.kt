package com.dss886.dotaautochess.feature.filter.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.data.Price
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.widget.TitleView
import java.io.Serializable

/**
 * Created by dss886 on 2019/1/25.
 */
class FilterHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTitleView: TitleView = itemView as TitleView

    fun bind(filterValue: Serializable?) {
        when (filterValue) {
            is Price -> mTitleView.setText(filterValue.desc)
            is Species -> mTitleView.setText(filterValue.desc)
            is Profession -> mTitleView.setText(filterValue.desc)
        }
    }

}
