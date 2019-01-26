package com.dss886.dotaautochess;

import android.os.Bundle;
import android.view.MenuItem;

import com.dss886.dotaautochess.feature.hero.HeroFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private final Fragment mDataFragment = new HeroFragment();
    private final Fragment mDashboardFragment = new DashboardFragment();
    private final Fragment mNewsFragment = new NewsFragment();
    private final FragmentManager mFragmentManager = getSupportFragmentManager();
    private Fragment mActiveFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_data:
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(mDataFragment).commit();
                    mActiveFragment = mDataFragment;
                    return true;
                case R.id.navigation_dashboard:
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(mDashboardFragment).commit();
                    mActiveFragment = mDashboardFragment;
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
                .add(R.id.main_container, mDashboardFragment, "3")
                .hide(mDashboardFragment)
                .add(R.id.main_container, mDataFragment, "1")
                .commit();
        mActiveFragment = mDataFragment;
    }

}
