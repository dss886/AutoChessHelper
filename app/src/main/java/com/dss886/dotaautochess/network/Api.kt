package com.dss886.dotaautochess.network

import android.os.Handler
import android.os.Looper
import com.dss886.dotaautochess.utils.Logger
import okhttp3.*
import java.io.IOException

/**
 * Created by dss886 on 2019/2/2.
 */
object Api {

    private val mHandler = Handler(Looper.getMainLooper())
    private val client = OkHttpClient()

    fun get(url: String, success : (response: Response) -> Unit, failure: (() -> Unit)? = null) {
        Logger.d("Api", "[get] url = $url")
        val request = Request.Builder()
                .url(url)
                .build()
        Api.client.newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        // Why it is in workerThread here???
                        mHandler.post { failure?.invoke() }
                    }
                    override fun onResponse(call: Call, response: Response) {
                        success.invoke(response)
                    }
                })
    }

}