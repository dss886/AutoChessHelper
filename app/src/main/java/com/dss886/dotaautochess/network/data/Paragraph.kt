package com.dss886.dotaautochess.network.data

import org.json.JSONObject

/**
 * Created by dss886 on 2019/2/2.
 */
class Paragraph {

    companion object {
        const val TYPE_TEXT = "text"
        const val TYPE_IMAGE = "image"
    }

    var type: String = TYPE_TEXT

    var content: String? = null

    var imageUrl: String? = null

    fun parseData(json: JSONObject?) {
        json ?: return
        type = json.optString("type")
        content = json.optString("content")
        imageUrl = json.optString("url")
                ?.replace("http://", "https://")
    }

}