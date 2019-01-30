package com.dss886.dotaautochess.feature.match.holder;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.utils.Logger;
import com.dss886.dotaautochess.utils.UIUtils;
import com.dss886.dotaautochess.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/28.
 */
public abstract class AbsOverviewHolder<T> extends RecyclerView.ViewHolder {

    private TitleView mTitleView;
    private List<TextView> mNameList = new ArrayList<>();

    /**
     * R.layout.match_item_overview
     */
    AbsOverviewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = itemView.findViewById(R.id.title_view);
        int[] itemIds = new int[]{
                R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5,
                R.id.item6, R.id.item7, R.id.item8, R.id.item9, R.id.item10
        };
        for (int id : itemIds) {
            mNameList.add((TextView) itemView.findViewById(id));
        }
    }

    abstract boolean isBuffEnable(T t, int count);

    abstract String getItemName(T t);

    abstract int getItemColorRes(T t);

    public void bind(int titleRes, List<Pair<T, Integer>> dataList) {
        Context context = mTitleView.getContext();
        mTitleView.setText(context.getString(titleRes));
        for (int i = 0; i < mNameList.size(); i++) {
            TextView nameTv = mNameList.get(i);
            if (i < dataList.size()) {
                Pair<T, Integer> pair = dataList.get(i);
                nameTv.setVisibility(View.VISIBLE);
                if (pair.first != null && pair.second != null) {
                    bindItem(context, nameTv, pair.first, pair.second);
                }
            } else {
                nameTv.setVisibility(View.GONE);
            }
        }
    }

    private void bindItem(Context context, TextView textView, T t, int count) {
        if (context == null || textView == null || t == null) {
            return;
        }
        if (count == 1) {
            textView.setText(getItemName(t));
        } else {
            textView.setText(context.getString(R.string.match_species_and_profession_count, getItemName(t), count));
        }
        if (textView.getBackground() instanceof GradientDrawable) {
            GradientDrawable drawable = ((GradientDrawable) textView.getBackground());
            if (isBuffEnable(t, count)) {
                int color = ContextCompat.getColor(context, getItemColorRes(t));
                // avoid color of the text and background is too close
                int colorBrightness = UIUtils.getColorBrightness(color);
                int textColor = ContextCompat.getColor(context, colorBrightness > 130
                        ? R.color.black_bb: R.color.white_bb);
                Logger.d("AbsOverviewHolder", getItemName(t) + colorBrightness);
                drawable.setColor(color);
                textView.setTextColor(textColor);
                textView.setAlpha(0.7f);
            } else {
                textView.setAlpha(1f);
                textView.setTextColor(ContextCompat.getColor(context, R.color.white_66));
                drawable.setColor(ContextCompat.getColor(context, R.color.white_0a));
            }
            textView.getBackground().invalidateSelf();
        }
    }

}
