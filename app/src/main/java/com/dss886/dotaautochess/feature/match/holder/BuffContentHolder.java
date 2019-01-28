package com.dss886.dotaautochess.feature.match.holder;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Buff;
import com.dss886.dotaautochess.data.IBuffHolder;
import com.dss886.dotaautochess.utils.BuffUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/27.
 */
public class BuffContentHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private TextView mContent;
    private View mDivider;

    /**
     * R.layout.match_item_buff_content
     */
    public BuffContentHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.buff_title);
        mContent = itemView.findViewById(R.id.buff_content);
        mDivider = itemView.findViewById(R.id.divider);
    }

    public void bind(IBuffHolder buffHolder, int count, boolean hideDivider) {
        Context context = itemView.getContext();
        mTitle.setText(buffHolder.getBuffName());
        String description = BuffUtils.getBuffDescription(context, buffHolder);
        if (BuffUtils.isBuffEnable(buffHolder.getBuffList(), count)) {
            mTitle.setTextColor(ContextCompat.getColor(context, buffHolder.getColorRes()));
            String[] lines = description.split("\n");
            if (buffHolder.getBuffList().size() == lines.length) {
                SpannableString ss = new SpannableString(description);
                int start = 0;
                for (int i = 0; i < lines.length; i++) {
                    Buff buff = buffHolder.getBuffList().get(i);
                    if (count >= buff.count) {
                        int color = ContextCompat.getColor(context, R.color.white_88);
                        ss.setSpan(new ForegroundColorSpan(color), start, start + lines[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        start += lines[i].length() + 1;
                    }
                }
                mContent.setText(ss);
            }
        } else {
            mTitle.setTextColor(ContextCompat.getColor(context, R.color.white_22));
            mContent.setText(description);
        }
        mDivider.setVisibility(hideDivider ? View.GONE: View.VISIBLE);
    }

}
