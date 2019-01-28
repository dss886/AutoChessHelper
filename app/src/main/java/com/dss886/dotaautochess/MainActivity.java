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

    private final Fragment mHeroFragment = new HeroFragment();
    private final Fragment mMatchFragment = new MatchFragment();
    private final Fragment mNewsFragment = new NewsFragment();
    private final FragmentManager mFragmentManager = getSupportFragmentManager();
    private Fragment mActiveFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_data:
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(mHeroFragment).commit();
                    mActiveFragment = mHeroFragment;
                    return true;
                case R.id.navigation_dashboard:
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(mMatchFragment).commit();
                    mActiveFragment = mMatchFragment;
                    return true;
                case R.id.navigation_news:
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(mNewsFragment).commit();
                    mActiveFragment = mNewsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager.beginTransaction()
                .add(R.id.main_container, mNewsFragment, "3")
                .hide(mNewsFragment)
                .add(R.id.main_container, mMatchFragment, "3")
                .hide(mMatchFragment)
                .add(R.id.main_container, mHeroFragment, "1")
                .commit();
        mActiveFragment = mHeroFragment;
    }

}
