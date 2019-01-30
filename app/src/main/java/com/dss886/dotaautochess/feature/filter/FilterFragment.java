package com.dss886.dotaautochess.feature.filter;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Price;
import com.dss886.dotaautochess.data.Profession;
import com.dss886.dotaautochess.data.Species;
import com.dss886.dotaautochess.widget.SimpleGridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Created by dss886 on 2019/1/29.
 */
public class FilterFragment extends Fragment {

    private SimpleGridLayout mPriceGridLayout;
    private SimpleGridLayout mSpeciesGridLayout;
    private SimpleGridLayout mProfessionGridLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        mPriceGridLayout = view.findViewById(R.id.grid_layout_price);
        mSpeciesGridLayout = view.findViewById(R.id.grid_layout_species);
        mProfessionGridLayout = view.findViewById(R.id.grid_layout_profession);
        bindPriceLayout(view.getContext());
        bindSpeciesLayout(view.getContext());
        bindProfessionLayout(view.getContext());
        return view;
    }

    private void bindPriceLayout(Context context) {
        for (final Price price : Price.values()) {
            TextView textView = generateFilterTextView(context);
            textView.setTextColor(ContextCompat.getColor(context, price.colorRes));
            textView.setText(price.description);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() instanceof IFilterController) {
                        ((IFilterController) getActivity()).goDetail(IFilterController.DETAIL_TYPE_PRICE, price);
                    }
                }
            });
            mPriceGridLayout.addView(textView);
        }
    }

    private void bindSpeciesLayout(Context context) {
        for (final Species species : Species.values()) {
            TextView textView = generateFilterTextView(context);
            textView.setTextColor(ContextCompat.getColor(context, species.colorRes));
            textView.setText(species.name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() instanceof IFilterController) {
                        ((IFilterController) getActivity()).goDetail(IFilterController.DETAIL_TYPE_SPECIES, species);
                    }
                }
            });
            mSpeciesGridLayout.addView(textView);
        }
    }

    private void bindProfessionLayout(Context context) {
        for (final Profession profession : Profession.values()) {
            TextView textView = generateFilterTextView(context);
            textView.setTextColor(ContextCompat.getColor(context, profession.colorRes));
            textView.setText(profession.name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() instanceof IFilterController) {
                        ((IFilterController) getActivity()).goDetail(IFilterController.DETAIL_TYPE_PROFESSION, profession);
                    }
                }
            });
            mProfessionGridLayout.addView(textView);
        }
    }

    private TextView generateFilterTextView(Context context) {
        if (context == null) {
            return null;
        }
        TextView textView = new TextView(context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.white_0a));
        textView.setAlpha(0.87f);
        return textView;
    }
}
