package com.dss886.dotaautochess.app

import android.content.Context
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

/**
 * Created by dss886 on 2019/1/30.
 */
open class App : android.app.Application() {

    companion object {
        lateinit var inst: App protected set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        inst = this
    }

    override fun onCreate() {
        super.onCreate()
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null)
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO)
    }

}
