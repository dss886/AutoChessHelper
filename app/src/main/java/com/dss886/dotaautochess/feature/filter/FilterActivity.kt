package com.dss886.dotaautochess.feature.filter

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dss886.dotaautochess.R
import java.io.Serializable

/**
 * Created by dss886 on 2019/1/29.
 */
class FilterActivity : AppCompatActivity(), IFilterController {

    private val mFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mFragmentManager.beginTransaction()
                .add(R.id.main_container, FilterFragment())
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun goDetail(type: Int, data: Serializable) {
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
