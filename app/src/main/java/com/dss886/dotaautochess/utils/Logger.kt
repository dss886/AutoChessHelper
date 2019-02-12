package com.dss886.dotaautochess.utils

import android.content.Context
import android.util.Log

import com.dss886.dotaautochess.BuildConfig
import com.umeng.analytics.MobclickAgent

/**
 * Created by dss886 on 2019/1/28.
 */
object Logger {

    fun d(tag: String, content: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content)
        }
    }

    fun onEvent(context: Context?, event: String, label: String) {
        if (BuildConfig.DEBUG) {
            Log.d("Logger.onEvent" , "[$event] $label")
        }
        MobclickAgent.onEvent(context, event, label)
    }

}
