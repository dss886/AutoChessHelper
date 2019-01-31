package com.dss886.dotaautochess.feature.filter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.feature.filter.list.FilterListFragment
import com.dss886.dotaautochess.feature.match.manager.MatchChangeListener
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import java.io.Serializable

/**
 * Created by dss886 on 2019/1/29.
 */
class FilterActivity : AppCompatActivity(), IFilterController, MatchChangeListener {

    private val mFragmentManager = supportFragmentManager
    private val mFilterFragment = FilterFragment()
    private val mDetailFragment = FilterListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mFragmentManager.beginTransaction()
                .add(R.id.main_container, mDetailFragment)
                .hide(mDetailFragment)
                .add(R.id.main_container, mFilterFragment)
                .commit()

        MatchManager.registerChangeListener(this)
    }

    override fun onDestroy() {
        MatchManager.unregisterChangeListener(this)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filter, menu)
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
        mDetailFragment.arguments = Bundle().apply {
            putSerializable(FilterListFragment.BUNDLE_VALUE, data)
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

    override fun tryToAddHero() {

    }

    override fun onHeroesChanged() {

    }

}
