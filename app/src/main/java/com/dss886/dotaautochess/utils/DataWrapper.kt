package com.dss886.dotaautochess.utils

/**
 * Created by dss886 on 2019/1/31.
 *
 * Used For multi-type adapter of RecyclerView.
 * Warning: It is a very simple but shabby way to support multi-type, be careful...
 */
class DataWrapper internal constructor(internal var type: Int, internal var data: Any) {

    internal fun <T> get(): T {
        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}
