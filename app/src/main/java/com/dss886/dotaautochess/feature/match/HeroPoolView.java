package com.dss886.dotaautochess.feature.match;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Hero;

import java.util.List;

/**
 * Created by dss886 on 2019/1/27.
 */
public class HeroPoolView extends ViewGroup {

    private float mItemMargin;

    public HeroPoolView(Context context) {
        this(context, null);
    }

    public HeroPoolView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeroPoolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mItemMargin = context.getResources().getDimension(R.dimen.match_hero_pool_item_margin);
        for (int i = 0; i < 10; i++) {
            addView(new ImageView(context), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float width = MeasureSpec.getSize(widthMeasureSpec);
        float childWidth = (width - 4 * mItemMargin) / 5;
        float childHeight = childWidth * 144 / 256;
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) childWidth, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) childHeight, MeasureSpec.EXACTLY);
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (childHeight * 2 + mItemMargin), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
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
            if ((i + 1) % 5 == 0) {
                lineCount++;
                left = 0;
                top = (child.getMeasuredHeight() + (int) mItemMargin) * lineCount;
            } else {
                left += child.getMeasuredWidth() + (int) mItemMargin;
            }
        }
    }

    public void bind(List<Hero> heroes) {
        for (int i = 0; i < getChildCount(); i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (i < heroes.size()) {
                imageView.setVisibility(VISIBLE);
                Glide.with(getContext()).load(heroes.get(i).iconRes).into(imageView);
            } else {
                imageView.setVisibility(GONE);
            }
        }
    }

}
