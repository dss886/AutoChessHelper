package com.dss886.dotaautochess.feature.filter;

import android.os.Bundle;
import android.view.MenuItem;

import com.dss886.dotaautochess.R;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

/**
 * Created by dss886 on 2019/1/29.
 */
public class FilterActivity extends AppCompatActivity implements IFilterController {

    private final FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragmentManager.beginTransaction()
                .add(R.id.main_container, new FilterFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goDetail(int type, Serializable data) {
//        Bundle arguments = new Bundle();
//        arguments.putInt(FilterListFragment.BUNDLE_TYPE, type);
//        arguments.putSerializable(FilterListFragment.BUNDLE_VALUE, data);
//        FilterListFragment fragment = new FilterListFragment();
//        fragment.setArguments(arguments);
//        mFragmentManager.beginTransaction()
//                .replace(R.id.main_container, fragment)
//                .addToBackStack(null)
//                .commit();
    }
}
