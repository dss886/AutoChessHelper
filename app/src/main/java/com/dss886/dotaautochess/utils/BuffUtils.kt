package com.dss886.dotaautochess.utils

import android.content.Context
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Buff
import com.dss886.dotaautochess.data.IBuffHolder

/**
 * Created by dss886 on 2019/1/28.
 */
object BuffUtils {

    fun isBuffEnable(buffList: List<Buff>, count: Int): Boolean {
        return if (buffList.isEmpty()) false else {
            count >= buffList[0].count
        }
    }

    fun getBuffDescription(context: Context, buffHolder: IBuffHolder): String {
        return buffHolder.buffList.joinToString("\n") { buff ->
            context.getString(R.string.data_buff_content, buff.count, buffHolder.desc, buff.description)
        }
    }

}
