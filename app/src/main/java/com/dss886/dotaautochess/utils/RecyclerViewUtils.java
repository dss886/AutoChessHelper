package com.dss886.dotaautochess.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/27.
 */
public class RecyclerViewUtils {

    public static void scrollItemToCenter(final RecyclerView recyclerView, View itemView, int offset) {
        if (recyclerView == null || itemView == null) {
            return;
        }
        final Rect rect = new Rect();
        recyclerView.offsetDescendantRectToMyCoords(itemView, rect);
        int targetPosition = (recyclerView.getHeight() - itemView.getHeight()) / 2 - offset;
        recyclerView.smoothScrollBy(0, rect.top - targetPosition);
    }

    public static void scrollItemToCompletelyVisible(final RecyclerView recyclerView, View itemView, int offset) {
        if (recyclerView == null || itemView == null) {
            return;
        }
        final Rect rect = new Rect();
        recyclerView.offsetDescendantRectToMyCoords(itemView, rect);
        rect.bottom = rect.top + itemView.getHeight();
        if (rect.bottom + offset > recyclerView.getHeight()) {
            recyclerView.smoothScrollBy(0, rect.bottom + offset - recyclerView.getHeight());
        }
    }

}
