package com.dss886.dotaautochess.widget

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/31.
 */
class ProgressDialog(context: Context) : Dialog(context) {

    init {
        setContentView(LayoutInflater.from(context).inflate(R.layout.widget_dialog_progress, null))
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}
