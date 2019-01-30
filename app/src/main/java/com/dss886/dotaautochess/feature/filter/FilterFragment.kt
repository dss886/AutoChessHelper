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
            val textView = generateFilterTextView(context)
            textView.setTextColor(ContextCompat.getColor(context, price.colorRes))
            textView.text = price.description
            textView.setOnClickListener {
                if (activity is IFilterController) {
                    (activity as IFilterController).goDetail(IFilterController.DETAIL_TYPE_PRICE, price)
                }
            }
            mPriceGridLayout?.addView(textView)
        }
    }

    private fun bindSpeciesLayout(context: Context) {
        for (species in Species.values()) {
            val textView = generateFilterTextView(context)
            textView.setTextColor(ContextCompat.getColor(context, species.colorRes))
            textView.text = species.desc
            textView.setOnClickListener {
                if (activity is IFilterController) {
                    (activity as IFilterController).goDetail(IFilterController.DETAIL_TYPE_SPECIES, species)
                }
            }
            mSpeciesGridLayout?.addView(textView)
        }
    }

    private fun bindProfessionLayout(context: Context) {
        for (profession in Profession.values()) {
            val textView = generateFilterTextView(context)
            textView.setTextColor(ContextCompat.getColor(context, profession.colorRes))
            textView.text = profession.desc
            textView.setOnClickListener {
                if (activity is IFilterController) {
                    (activity as IFilterController).goDetail(IFilterController.DETAIL_TYPE_PROFESSION, profession)
                }
            }
            mProfessionGridLayout?.addView(textView)
        }
    }

    private fun generateFilterTextView(context: Context): TextView {
        val textView = TextView(context)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        textView.gravity = Gravity.CENTER
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.white_0a))
        textView.alpha = 0.87f
        return textView
    }
}
