package com.dss886.dotaautochess.data

import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/25.
 */
enum class Price constructor(var price: Int, var colorRes: Int, var description: String) {

    GOLD_1(1, R.color.palette_20, "普通"),
    GOLD_2(2, R.color.palette_160, "罕见"),
    GOLD_3(3, R.color.palette_140, "稀有"),
    GOLD_4(4, R.color.palette_180, "神话"),
    GOLD_5(5, R.color.palette_70, "传说")

}
