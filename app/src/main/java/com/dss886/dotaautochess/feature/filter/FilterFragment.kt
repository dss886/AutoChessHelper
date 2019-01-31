package com.dss886.dotaautochess.feature.filter

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Price
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import com.dss886.dotaautochess.widget.SimpleGridLayout

/**
 * Created by dss886 on 2019/1/29.
 */
class FilterFragment : Fragment() {

    private var mPriceGridLayout: SimpleGridLayout? = null
    private var mSpeciesGridLayout: SimpleGridLayout? = null
    private var mProfessionGridLayout: SimpleGridLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        mPriceGridLayout = view.findViewById(R.id.grid_layout_price)
        mSpeciesGridLayout = view.findViewById(R.id.grid_layout_species)
        mProfessionGridLayout = view.findViewById(R.id.grid_layout_profession)
        bindPriceLayout(view.context)
        bindSpeciesLayout(view.context)
        bindProfessionLayout(view.context)
        return view
    }

    private fun bindPriceLayout(context: Context) {
        for (price in Price.values()) {
            val textView = generateFilterTextView(context, price.colorRes)
            textView.text = price.desc
            textView.setOnClickListener {
                if (activity is IFilterController) {
                    (activity as IFilterController).goDetail(price)
                }
            }
            mPriceGridLayout?.addView(textView)
        }
    }

    private fun bindSpeciesLayout(context: Context) {
        val speciesList = Species.values().apply {
                sortByDescending { ContextCompat.getColor(context, it.colorRes)
            }
        }
        for (species in speciesList) {
            val textView = generateFilterTextView(context, species.colorRes)
            textView.text = species.desc
            textView.setOnClickListener {
                if (activity is IFilterController) {
                    (activity as IFilterController).goDetail(species)
                }
            }
            mSpeciesGridLayout?.addView(textView)
        }
    }

    private fun bindProfessionLayout(context: Context) {
        val professionList = Profession.values().apply {
            sortByDescending { ContextCompat.getColor(context, it.colorRes)
            }
        }
        for (profession in professionList) {
            val textView = generateFilterTextView(context, profession.colorRes)
            textView.text = profession.desc
            textView.setOnClickListener {
                if (activity is IFilterController) {
                    (activity as IFilterController).goDetail(profession)
                }
            }
            mProfessionGridLayout?.addView(textView)
        }
    }

    private fun generateFilterTextView(context: Context, colorRes: Int): TextView {
        val textView = TextView(context)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        textView.gravity = Gravity.CENTER
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.white_0a))
        textView.setTextColor(ContextCompat.getColor(context,colorRes))
        textView.alpha = 0.9f
        return textView
    }

}
