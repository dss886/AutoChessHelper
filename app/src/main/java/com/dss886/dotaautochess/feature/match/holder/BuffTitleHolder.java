package com.dss886.dotaautochess.feature.match.holder;

import android.view.View;
import android.widget.TextView;

import com.dss886.dotaautochess.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/27.
 */
public class BuffTitleHolder extends RecyclerView.ViewHolder {

    /**
     * R.layout.match_item_buff_title
     */
    public BuffTitleHolder(@NonNull View itemView) {
        super(itemView);
        TextView name = itemView.findViewById(R.id.name);
        name.setText(itemView.getContext().getString(R.string.match_buff_title));
    }

}
