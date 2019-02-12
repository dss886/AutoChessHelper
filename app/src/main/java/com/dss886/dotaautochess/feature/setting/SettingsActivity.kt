package com.dss886.dotaautochess.feature.setting

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.app.BaseActivity

/**
 * Created by dss886 on 2019/2/2.
 */

class SettingsActivity : BaseActivity() , ISettingsController {

    companion object {
        const val TAG_SETTINGS = "settings"
        const val TAG_OPEN_SOURCE = "open_source"
    }

    private val mFragmentManager = supportFragmentManager
    private val mSettingsFragment = SettingsFragment()
    private val mOpenSourceFragment = OpenSourceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mFragmentManager.beginTransaction()
                .add(R.id.main_container, mOpenSourceFragment, TAG_OPEN_SOURCE)
                .hide(mOpenSourceFragment)
                .add(R.id.main_container, mSettingsFragment, TAG_SETTINGS)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        title = getString(R.string.title_settings)
    }

    override fun goPage(tag: String, title: CharSequence) {
        val transaction = mFragmentManager.beginTransaction()
        mFragmentManager.findFragmentByTag(tag)?.let { fragment ->
            transaction
                    .setCustomAnimations(
                        R.anim.anim_fragment_enter,
                        R.anim.anim_fragment_exit,
                        R.anim.anim_fragment_pop_enter,
                        R.anim.anim_fragment_pop_exit
                    )
                    .hide(mSettingsFragment)
                    .show(fragment)
                    .addToBackStack(null)
                    .commit()
        }
        setTitle(title)
    }

}