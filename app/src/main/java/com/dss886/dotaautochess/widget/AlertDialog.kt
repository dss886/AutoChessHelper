package com.dss886.dotaautochess.widget

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/31.
 */
class AlertDialog(context: Context) : Dialog(context) {

    var mTitle : TextView
    var mCancel : TextView
    var mAccept : TextView

    init {
        setContentView(LayoutInflater.from(context).inflate(R.layout.widget_dialog_alert, null))
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        mTitle = findViewById(R.id.title)
        mCancel = findViewById(R.id.cancel)
        mAccept = findViewById(R.id.accept)
    }

}
