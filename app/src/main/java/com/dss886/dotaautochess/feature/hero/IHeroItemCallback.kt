package com.dss886.dotaautochess.feature.hero

import com.dss886.dotaautochess.data.Hero

/**
 * Created by dss886 on 2019/1/30.
 */
interface IHeroItemCallback {

    fun onItemClick(position: Int, hero : Hero)

}