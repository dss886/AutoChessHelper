package com.dss886.dotaautochess.network.data

import org.json.JSONObject

/**
 * Created by dss886 on 2019/2/2.
 */
class Timeline {

    val feedList = mutableListOf<Feed>()

    var lastFeedId: Long = 0

    fun parseData(jsonStr: String?) {
        jsonStr ?: return
        try {
            val json = JSONObject(jsonStr)
            val feeds = json.optJSONArray("feeds")
            for (i in 0 until feeds.length()) {
                val feedObject = feeds.optJSONObject(i)?.optJSONObject("feed")
                val feed = Feed()
                feed.parseData(feedObject)
                if (feed.id > 0L) {
                    feedList.add(feed)
                }
                lastFeedId = feedObject?.optLong("id", 0L) ?: 0L
            }
        } catch (ignore: Throwable) {
        }
    }
}