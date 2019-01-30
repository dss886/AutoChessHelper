package com.dss886.dotaautochess

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dss886.dotaautochess.feature.hero.HeroFragment
import com.dss886.dotaautochess.feature.match.MatchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG_FRAGMENT_HERO = "1"
        private const val TAG_FRAGMENT_MATCH = "2"
        private const val TAG_FRAGMENT_NEWS = "3"
    }

    private val mHeroFragment = HeroFragment()
    private val mMatchFragment = MatchFragment()
    private val mNewsFragment = NewsFragment()
    private val mFragmentManager = supportFragmentManager
    private var mActiveFragment: Fragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_data -> switchFragment(mHeroFragment)
            R.id.navigation_dashboard -> switchFragment(mMatchFragment)
            R.id.navigation_news -> switchFragment(mNewsFragment)
            else -> false
        }
    }

    /**
     * In some cases, activity recovery will cause some issues in FragmentManager.
     * As we really have nothing to restore, we simply disable it
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        setContentView(R.layout.activity_main)
        savedInstanceState?.clear()

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mFragmentManager.beginTransaction()
                .add(R.id.main_container, mNewsFragment, TAG_FRAGMENT_NEWS)
                .hide(mNewsFragment)
                .add(R.id.main_container, mMatchFragment, TAG_FRAGMENT_MATCH)
                .hide(mMatchFragment)
                .add(R.id.main_container, mHeroFragment, TAG_FRAGMENT_HERO)
                .commit()
        mActiveFragment = mHeroFragment
    }

    private fun switchFragment(fragment: Fragment): Boolean {
        mFragmentManager.beginTransaction()
                .hide(mActiveFragment!!)
                .show(fragment)
                .commit()
        mActiveFragment = fragment
        return true
    }

}
