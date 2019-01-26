package com.dss886.dotaautochess.feature.hero;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.dss886.dotaautochess.R;
import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Created by dss886 on 2019/1/26.
 */
@SuppressWarnings("unused")
public class HeroHeaderBehavior extends CoordinatorLayout.Behavior<HeroHeaderView> {

    private float mHeaderMoveDistance;

    public HeroHeaderBehavior() {}

    public HeroHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeaderMoveDistance = context.getResources().getDimension(R.dimen.hero_header_height)
                - context.getResources().getDimension(R.dimen.toolbar_height);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull HeroHeaderView child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull HeroHeaderView child, @NonNull View dependency) {
        if (mHeaderMoveDistance == 0) {
            return false;
        }
        float percent = -dependency.getY() / mHeaderMoveDistance;
        if (percent < 0) {
            percent = 0;
        }
        child.onCollapsing(percent);
        return true;
    }
}
