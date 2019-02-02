package com.dss886.dotaautochess.network.data

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by dss886 on 2019/2/1.
 */
class Feed {

    var id: Long = 0

    var title: String? = null

    var coverUrl: String? = null

    var shortContent: String? = null

    var userName: String? = null

    var userAvatarUrl: String? = null

    var createTime: Long = 0

    var replyCount: Int = 0

    fun parseData(feed: JSONObject?) {
        feed ?: return
        try {
            val data = feed.optString("data")
            val jsonArray = JSONArray(data)
            if (jsonArray.length() <= 0) return
            val json = jsonArray.optJSONObject(0)
            id = json.optLong("id")
            title = json.optString("title")
            coverUrl = json.optString("cover")
                    ?.replace("http://", "https://")
            shortContent = json.optString("simpleContent")
            userName = json.optString("username")
            userAvatarUrl = json.optString("userhead")
                    ?.replace("http://", "https://")
            createTime = json.optLong("created")
            replyCount = json.optInt("reply")
        } catch (ignore: Throwable) {
        }
    }

}