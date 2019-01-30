package com.dss886.dotaautochess.utils

import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by dss886 on 2019/1/27.
 */
object RecyclerViewUtils {

    fun scrollItemToCenter(recyclerView: RecyclerView?, itemView: View?, offset: Int) {
        if (recyclerView == null || itemView == null) {
            return
        }
        val rect = Rect()
        recyclerView.offsetDescendantRectToMyCoords(itemView, rect)
        val targetPosition = (recyclerView.height - itemView.height) / 2 - offset
        recyclerView.smoothScrollBy(0, rect.top - targetPosition)
    }

    fun scrollItemToCompletelyVisible(recyclerView: RecyclerView?, itemView: View?, offset: Int) {
        if (recyclerView == null || itemView == null) {
            return
        }
        val rect = Rect()
        recyclerView.offsetDescendantRectToMyCoords(itemView, rect)
        rect.bottom = rect.top + itemView.height
        if (rect.bottom + offset > recyclerView.height) {
            recyclerView.smoothScrollBy(0, rect.bottom + offset - recyclerView.height)
        }
    }

}
