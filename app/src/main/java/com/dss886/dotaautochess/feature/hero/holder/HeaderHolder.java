package com.dss886.dotaautochess.feature.hero.holder;

import android.view.View;

import com.dss886.dotaautochess.widget.TitleView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
public class HeaderHolder extends RecyclerView.ViewHolder {

    private TitleView mTitleView;

    public HeaderHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = ((TitleView) itemView);
    }

    public void bind(String level) {
        mTitleView.setText(level);
    }

}
