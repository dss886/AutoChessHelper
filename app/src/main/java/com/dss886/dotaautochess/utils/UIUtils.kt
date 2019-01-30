package com.dss886.dotaautochess.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * Created by dss886 on 2019/1/26.
 */
object UIUtils {

    const val KEEP = -3

    fun getScreenWidth(context: Context?): Int {
        return if (context == null) 0 else {
            val dm = context.resources.displayMetrics
            dm?.widthPixels ?: 0
        }
    }

    fun updateLayout(view: View?, w: Int, h: Int) {
        if (view != null) {
            val params = view.layoutParams
            if (params != null && (params.width != w || params.height != h)) {
                if (w != KEEP) {
                    params.width = w
                }
                if (h != KEEP) {
                    params.height = h
                }
                view.layoutParams = params
            }
        }
    }

    fun updateLayoutMargin(view: View?, l: Int, t: Int, r: Int, b: Int) {
        if (view != null) {
            val params = view.layoutParams
            if (params != null) {
                if (params is ViewGroup.MarginLayoutParams) {
                    if (l != KEEP) {
                        params.leftMargin = l
                    }
                    if (t != KEEP) {
                        params.topMargin = t
                    }
                    if (r != KEEP) {
                        params.rightMargin = r
                    }
                    if (b != KEEP) {
                        params.bottomMargin = b
                    }
                    view.layoutParams = params
                }
            }
        }
    }

    fun getColorBrightness(color: Int): Int {
        return ((color and 0xff) * 19595
                + (color shr 8 and 0xff) * 38469
                + (color shr 16 and 0xff) * 7472) shr 16
    }

    fun setVisibility(view: View?, visibility: Int) {
        if (view != null && view.visibility != visibility) {
            view.visibility = visibility
        }
    }

}
