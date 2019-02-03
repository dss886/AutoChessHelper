package com.dss886.dotaautochess.feature.setting

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.app.App
import com.dss886.dotaautochess.network.Api
import com.dss886.dotaautochess.utils.Constants
import com.dss886.dotaautochess.widget.AlertDialog
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import java.lang.ref.WeakReference

/**
 * Created by dss886 on 2019/2/3.
 */
object UpdateManager {

    private const val KEY_LAST_TIME = "last_time"
    private const val CHECK_TIME_INTERVAL = 20 * 60 * 60 * 1000L

    private val mSp = App.inst.getSharedPreferences("update", Context.MODE_PRIVATE)

    val mAppVersion = getAppVersion()

    fun checkAppUpdate(contextRef: WeakReference<Context>,
                       isManually: Boolean = false,
                       hasUpdate : (() -> Unit)? = null,
                       hasNoUpdate : (() -> Unit)? = null,
                       networkError : (() -> Unit)? = null) {
        val lastCheckTime = mSp.getLong(KEY_LAST_TIME, 0L)
        if (!isManually && System.currentTimeMillis() - lastCheckTime < CHECK_TIME_INTERVAL) {
            return
        }
        mSp.edit().putLong(KEY_LAST_TIME, System.currentTimeMillis()).apply()

        Api.get(Constants.URL_UPDATE, success = { response ->
            doAsync {
                try {
                    JSONArray(response.body()?.string())
                            .takeIf { it.length() > 0 }
                            ?.optJSONObject(0)
                            ?.let { release ->
                                val versionName = release.optString("name")
                                val link = release
                                        .optJSONArray("assets")
                                        ?.takeIf { it.length() > 0 }
                                        ?.optJSONObject(0)
                                        ?.optString("browser_download_url")
                                uiThread {
                                    if (versionName != null && link != null) {
                                        onGetVersion(contextRef, versionName, link, hasUpdate, hasNoUpdate)
                                    } else {
                                        networkError?.invoke()
                                    }
                                }
                            }
                } catch (ignore: Throwable) {
                    uiThread {
                        networkError?.invoke()
                    }
                }
            }
        }, failure = {
            networkError?.invoke()
        })
    }

    private fun onGetVersion(contextRef: WeakReference<Context>, name: String, link: String,
                             hasUpdate : (() -> Unit)?, hasNoUpdate : (() -> Unit)?) {
        if (mAppVersion == name) {
            hasNoUpdate?.invoke()
            return
        }
        contextRef.get()?.let { context ->
            AlertDialog(context).apply {
                mTitle.text = context.getString(R.string.settings_update_tip, name)
                mCancel.text = context.getString(R.string.common_dialog_btn_cancel)
                mCancel.setOnClickListener { dismiss() }
                mAccept.text = context.getString(R.string.common_dialog_btn_confirm)
                mAccept.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
                }
            }.show()
            hasUpdate?.invoke()
        }
    }

    private fun getAppVersion(): String {
        var versionName = ""
        try {
            val pm = App.inst.packageManager
            val packageInfo = pm?.getPackageInfo(App.inst.packageName, PackageManager.GET_CONFIGURATIONS)
            versionName = packageInfo?.versionName ?: ""
        } catch (ignore: PackageManager.NameNotFoundException) {
        }
        return versionName
    }
}
