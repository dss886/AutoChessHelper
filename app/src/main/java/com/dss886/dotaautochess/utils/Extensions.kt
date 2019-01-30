package com.dss886.dotaautochess.utils

import com.dss886.dotaautochess.app.BaseApplication

/**
 * Created by dss886 on 2019/1/30.
 */

inline val Int.dp: Float
    get() = (BaseApplication.inst.resources.displayMetrics.density * this) + 0.5f

inline val Int.dpInt: Int
    get() = this.dp.toInt()