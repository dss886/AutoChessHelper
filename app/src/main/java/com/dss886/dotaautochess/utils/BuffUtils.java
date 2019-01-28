package com.dss886.dotaautochess.utils;

import android.content.Context;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Buff;
import com.dss886.dotaautochess.data.IBuffHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.core.util.Pair;

/**
 * Created by dss886 on 2019/1/28.
 */
public class BuffUtils {

    public static boolean isBuffEnable(List<Buff> buffList, int count) {
        if (buffList != null && buffList.size() > 0) {
            return count >= buffList.get(0).count;
        }
        return false;
    }

    public static String getBuffDescription(Context context, IBuffHolder buffHolder) {
        List<String> descList = new ArrayList<>();
        if (buffHolder != null && buffHolder.getBuffList() != null) {
            for (Buff buff : buffHolder.getBuffList()) {
                descList.add(context.getString(R.string.data_buff_content, buff.count, buffHolder.getName(), buff.description));
            }
        }
        return StringUtils.join("\n", descList);
    }

    public static <T extends IBuffHolder> int getEnableBuffCount(List<Pair<T, Integer>> pairList) {
        if (CollectionUtils.isEmpty(pairList)) {
            return 0;
        }
        int enableCount = 0;
        for (Pair<T, Integer> pair : pairList) {
            if (pair.first != null && !CollectionUtils.isEmpty(pair.first.getBuffList()) && pair.second != null) {
                if (pair.second >= pair.first.getBuffList().get(0).count) {
                    enableCount++;
                }
            }
        }
        return enableCount;
    }

}
