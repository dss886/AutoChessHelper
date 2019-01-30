package com.dss886.dotaautochess;

import android.os.Bundle;
import android.view.MenuItem;

import com.dss886.dotaautochess.feature.hero.HeroFragment;
import com.dss886.dotaautochess.feature.match.MatchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT_HERO = "1";
    private static final String TAG_FRAGMENT_MATCH = "2";
    private static final String TAG_FRAGMENT_NEWS = "3";

    private final Fragment mHeroFragment = new HeroFragment();
    private final Fragment mMatchFragment = new MatchFragment();
    private final Fragment mNewsFragment = new NewsFragment();
    private final FragmentManager mFragmentManager = getSupportFragmentManager();
    private Fragment mActiveFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_data:
                    switchFragment(mHeroFragment);
                    return true;
                case R.id.navigation_dashboard:
                    switchFragment(mMatchFragment);
                    return true;
                case R.id.navigation_news:
                    switchFragment(mNewsFragment);
                    return true;
            }
            return false;
        }
    };

    /**
     * In some cases, activity recovery will cause some issues in FragmentManager.
     * As we really have nothing to restore, we simply disable it
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            savedInstanceState.clear();
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager.beginTransaction()
                .add(R.id.main_container, mNewsFragment, TAG_FRAGMENT_NEWS)
                .hide(mNewsFragment)
                .add(R.id.main_container, mMatchFragment, TAG_FRAGMENT_MATCH)
                .hide(mMatchFragment)
                .add(R.id.main_container, mHeroFragment, TAG_FRAGMENT_HERO)
                .commit();
        mActiveFragment = mHeroFragment;
    }

    private void switchFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        mFragmentManager.beginTransaction()
                .hide(mActiveFragment)
                .show(fragment)
                .commit();
        mActiveFragment = fragment;
    }

}
