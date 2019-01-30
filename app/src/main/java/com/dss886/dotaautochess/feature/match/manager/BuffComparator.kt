package com.dss886.dotaautochess.feature.match.manager

import com.dss886.dotaautochess.data.IBuffHolder
import com.dss886.dotaautochess.utils.BuffUtils
import java.util.*

/**
 * Created by dss886 on 2019/1/27.
 * Comparator to sort list by IBuffHolder and its count.
 */
class BuffComparator : Comparator<Pair<IBuffHolder, Int>> {

    override fun compare(o1: Pair<IBuffHolder, Int>?, o2: Pair<IBuffHolder, Int>?): Int {
        if (o1 == null && o2 == null) return 0
        if (o1 == null) return 1
        if (o2 == null) return -1

        val enable1 = BuffUtils.isBuffEnable(o1.first.buffList, o1.second)
        val enable2 = BuffUtils.isBuffEnable(o2.first.buffList, o2.second)
        if (enable1 && !enable2) return -1
        return if (!enable1 && enable2) 1 else -Integer.compare(o1.second, o2.second)

    }

}
