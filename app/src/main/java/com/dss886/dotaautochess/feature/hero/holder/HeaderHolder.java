package com.dss886.dotaautochess.feature.hero.holder;

import android.view.View;
import android.widget.TextView;

import com.dss886.dotaautochess.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
public class HeaderHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public HeaderHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
    }

    public void bind(String level) {
        mTextView.setText(level);
    }

}
