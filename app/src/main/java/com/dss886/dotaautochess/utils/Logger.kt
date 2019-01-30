package com.dss886.dotaautochess.utils

import android.util.Log

import com.dss886.dotaautochess.BuildConfig

/**
 * Created by dss886 on 2019/1/28.
 */
object Logger {

    fun d(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content)
        }
    }

}
