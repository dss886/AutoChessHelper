package com.dss886.dotaautochess.network.data

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by dss886 on 2019/2/2.
 */

class Article {

    var createTime: Long = 0
    var userName: String? = null
    var userAvatarUrl: String? = null
    val paragraphList = mutableListOf<Paragraph>()

    fun parseData(jsonStr: String?) {
        jsonStr ?: return
        try {
            val json = JSONObject(jsonStr)
            val post = json.optJSONObject("bbsPost")
                    ?.optJSONObject("bbsPost")
            createTime = post?.optLong("created") ?: 0L
            userName = post?.optString("username")
            userAvatarUrl = post?.optString("userhead")
                    ?.replace("http://", "https://")
            val contentObj = JSONArray(post?.optString("contents"))
            for (i in 0 until contentObj.length()) {
                val paraObj = contentObj.optJSONObject(i)
                val para = Paragraph()
                para.parseData(paraObj)
                paragraphList.add(para)
            }
        } catch (ignore: Throwable) {
        }
    }

}