package com.dss886.dotaautochess.feature.filter

import java.io.Serializable

/**
 * Created by dss886 on 2019/1/30.
 */
interface IFilterController {

    companion object {
        const val DETAIL_TYPE_PRICE = 0
        const val DETAIL_TYPE_SPECIES = 1
        const val DETAIL_TYPE_PROFESSION = 2
    }

    fun goDetail(type: Int, data: Serializable)

}
