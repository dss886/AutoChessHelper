package com.dss886.dotaautochess.feature.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/2/2.
 */
class OpenSourceFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_open_source, container, false)

        view.findViewById<View>(R.id.image).apply {
            val url = context.getText(R.string.settings_open_source_image_link).toString()
            findViewById<TextView>(R.id.name).text = context.getText(R.string.settings_open_source_image_name)
            findViewById<TextView>(R.id.license).text = context.getText(R.string.settings_open_source_copyright_valve)
            findViewById<TextView>(R.id.link).text = url
            setOnClickListener {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }

        view.findViewById<View>(R.id.okhttp).apply {
            val url = context.getText(R.string.settings_open_source_okhttp_link).toString()
            findViewById<TextView>(R.id.name).text = context.getText(R.string.settings_open_source_okhttp_name)
            findViewById<TextView>(R.id.license).text = context.getText(R.string.settings_open_source_license_apache)
            findViewById<TextView>(R.id.link).text = url
            setOnClickListener {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }

        view.findViewById<View>(R.id.anko).apply {
            val url = context.getText(R.string.settings_open_source_anko_link).toString()
            findViewById<TextView>(R.id.name).text = context.getText(R.string.settings_open_source_anko_name)
            findViewById<TextView>(R.id.license).text = context.getText(R.string.settings_open_source_license_apache)
            findViewById<TextView>(R.id.link).text = url
            setOnClickListener {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
        return view
    }


}
