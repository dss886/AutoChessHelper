package com.dss886.dotaautochess.app

import android.content.Context

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

}
