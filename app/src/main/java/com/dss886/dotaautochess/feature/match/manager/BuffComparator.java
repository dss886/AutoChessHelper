package com.dss886.dotaautochess.feature.match.manager;

import com.dss886.dotaautochess.data.IBuffHolder;
import com.dss886.dotaautochess.utils.BuffUtils;

import java.util.Comparator;

import androidx.core.util.Pair;


/**
 * Created by dss886 on 2019/1/27.
 * Comparator to sort list by IBuffHolder and its count.
 */
public class BuffComparator implements Comparator<Pair<? extends IBuffHolder, Integer>> {

    @Override
    public int compare(Pair<? extends IBuffHolder, Integer> o1, Pair<? extends IBuffHolder, Integer> o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return 1;
        if (o2 == null) return -1;

        if (o1.first == null && o2.first == null) return 0;
        if (o1.first == null) return 1;
        if (o2.first == null) return -1;

        if (o1.second == null && o2.second == null) return 0;
        if (o1.second == null) return 1;
        if (o2.second == null) return -1;

        boolean enable1 = BuffUtils.isBuffEnable(o1.first.getBuffList(), o1.second);
        boolean enable2 = BuffUtils.isBuffEnable(o2.first.getBuffList(), o2.second);
        if (enable1 && !enable2) return -1;
        if (!enable1 && enable2) return 1;

        return -Integer.compare(o1.second, o2.second);
    }

}
