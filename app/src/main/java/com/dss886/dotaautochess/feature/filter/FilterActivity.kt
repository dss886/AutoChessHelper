package com.dss886.dotaautochess.feature.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.app.BaseActivity
import com.dss886.dotaautochess.feature.filter.list.FilterListFragment
import com.dss886.dotaautochess.utils.Constants
import java.io.Serializable

/**
 * Created by dss886 on 2019/1/29.
 */
class FilterActivity : BaseActivity(), IFilterController {

    companion object {
        fun startActivity(context: Context, value: Serializable) {
            val intent = Intent(context, FilterActivity::class.java)
            intent.putExtra(Constants.BUNDLE_VALUE, value)
            context.startActivity(intent)
        }
    }

    private var mIsOnlyDetail = false
    private val mFragmentManager = supportFragmentManager
    private val mFilterFragment = FilterFragment()
    private val mDetailFragment = FilterListFragment()
    private var mLastGoDetailTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val enterValue = intent.getSerializableExtra(Constants.BUNDLE_VALUE)
        mIsOnlyDetail = enterValue != null

        val transaction = mFragmentManager.beginTransaction()
        transaction.add(R.id.main_container, mDetailFragment)
        if (mIsOnlyDetail) {
            mDetailFragment.arguments = Bundle().apply {
                putSerializable(Constants.BUNDLE_VALUE, enterValue)
            }
            mDetailFragment.updateData()
        } else {
            transaction.hide(mDetailFragment).add(R.id.main_container, mFilterFragment)
        }
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (!mIsOnlyDetail) {
            menuInflater.inflate(R.menu.filter, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_done -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun goDetail(data: Serializable) {
        val now = System.currentTimeMillis()
        if (now - mLastGoDetailTime <= 300L) {
            return
        }
        mLastGoDetailTime = now

        mDetailFragment.arguments = Bundle().apply {
            putSerializable(Constants.BUNDLE_VALUE, data)
        }
        mDetailFragment.updateData()
        mFragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.anim.anim_fragment_enter,
                        R.anim.anim_fragment_exit,
                        R.anim.anim_fragment_pop_enter,
                        R.anim.anim_fragment_pop_exit
                )
                .hide(mFilterFragment)
                .show(mDetailFragment)
                .addToBackStack(null)
                .commit()
    }

}
