package com.dss886.dotaautochess.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.dss886.dotaautochess.R;

import androidx.annotation.Nullable;

/**
 * Created by dss886 on 2019/1/27.
 *
 * GridView and GridLayout cannot divide item on average (have 5~10px gap).
 * (Maybe it is that I didn't find the right way to use it...)
 * So I have to measure and layout these imageViews by myself.
 */
public class SimpleGridLayout extends ViewGroup {

    private int mColumnCount;
    private float mItemMargin;
    private int mItemRatioWidth;
    private int mItemRatioHeight;

    public SimpleGridLayout(Context context) {
        this(context, null);
    }

    public SimpleGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleGridLayout, 0, 0);
            mColumnCount = array.getInt(R.styleable.SimpleGridLayout_columnCount, 5);
            mItemMargin = array.getDimension(R.styleable.SimpleGridLayout_itemMargin, 0);
            mItemRatioWidth = array.getInt(R.styleable.SimpleGridLayout_itemRatioWidth, 0);
            mItemRatioHeight = array.getInt(R.styleable.SimpleGridLayout_itemRatioHeight, 0);
            array.recycle();
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int lineCount = (int) ((childCount - 1) * 1F / mColumnCount) + 1;
        float width = MeasureSpec.getSize(widthMeasureSpec);
        float childWidth = (width - (mColumnCount - 1) * mItemMargin) / mColumnCount;
        float childHeight = childWidth * mItemRatioHeight / mItemRatioWidth;
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) childWidth, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) childHeight, MeasureSpec.EXACTLY);
        float newHeight = childHeight * lineCount + mItemMargin * (lineCount - 1);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) newHeight, MeasureSpec.EXACTLY));
        measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int lineCount = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
            if ((i + 1) % mColumnCount == 0) {
                lineCount++;
                left = 0;
                top = (child.getMeasuredHeight() + (int) mItemMargin) * lineCount;
            } else {
                left += child.getMeasuredWidth() + (int) mItemMargin;
            }
        }
    }

}
