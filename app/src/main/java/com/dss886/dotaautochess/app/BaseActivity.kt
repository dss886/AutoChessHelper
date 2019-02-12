package com.dss886.dotaautochess.app

import androidx.appcompat.app.AppCompatActivity
import com.umeng.analytics.MobclickAgent

/**
 * Created by dss886 on 2019/2/12.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

}