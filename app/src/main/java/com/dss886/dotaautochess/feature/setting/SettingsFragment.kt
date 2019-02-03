package com.dss886.dotaautochess.feature.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.utils.Constants.URL_FEEDBACK
import com.dss886.dotaautochess.widget.AlertDialog
import com.dss886.dotaautochess.widget.ProgressDialog
import java.lang.ref.WeakReference

/**
 * Created by dss886 on 2019/2/2.
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        view.findViewById<TextView>(R.id.version).apply {
            text = UpdateManager.mAppVersion
        }
        view.findViewById<View>(R.id.feedback).apply {
            findViewById<TextView>(R.id.text).text = context.getText(R.string.settings_feedback)
            setOnClickListener {
                val uri = Uri.parse(URL_FEEDBACK)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
        view.findViewById<View>(R.id.open_source).apply {
            findViewById<TextView>(R.id.text).text = context.getText(R.string.settings_open_source)
            setOnClickListener {
                (context as? ISettingsController)?.goPage(SettingsActivity.TAG_OPEN_SOURCE,
                        context.getText(R.string.settings_open_source))
            }
        }
        view.findViewById<View>(R.id.check_upgrade).apply {
            findViewById<TextView>(R.id.text).text = context.getText(R.string.settings_check_upgrade)
            setOnClickListener {
                val dialog = ProgressDialog(context).apply { show() }
                UpdateManager.checkAppUpdate(WeakReference(context), true, hasUpdate = {
                    dialog.dismiss()
                }, hasNoUpdate = {
                    dialog.dismiss()
                    AlertDialog(context).apply {
                        mTitle.text = context.getString(R.string.settings_update_has_no_new)
                        mCancel.visibility = View.GONE
                        mAccept.text = context.getString(R.string.common_dialog_btn_ok)
                        mAccept.setOnClickListener { dismiss() }
                    }.show()
                }, networkError = {
                    dialog.dismiss()
                    Toast.makeText(context, R.string.common_network_error, Toast.LENGTH_SHORT).show()
                })
            }
        }
        return view
    }

}