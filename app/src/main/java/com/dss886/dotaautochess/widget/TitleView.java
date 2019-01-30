package com.dss886.dotaautochess.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dss886.dotaautochess.R;

import androidx.annotation.Nullable;

/**
 * Created by dss886 on 2019/1/29.
 */
public class TitleView extends LinearLayout {

    private TextView mTitleTextView;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        View.inflate(context, R.layout.widget_title_view, this);
        mTitleTextView = findViewById(R.id.title);
        if (attrs != null) {
            int[] textAttrId = { android.R.attr.text };
            TypedArray array = context.getTheme().obtainStyledAttributes(attrs, textAttrId, 0, 0);
            CharSequence text = array.getText(0);
            mTitleTextView.setText(text);
            array.recycle();
        }
        setGravity(Gravity.CENTER_VERTICAL);
    }

    public void setText(CharSequence text) {
        mTitleTextView.setText(text);
    }
}
