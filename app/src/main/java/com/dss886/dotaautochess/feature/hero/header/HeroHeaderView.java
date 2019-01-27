package com.dss886.dotaautochess.feature.hero.header;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.utils.UIUtils;

/**
 * Created by dss886 on 2019/1/26.
 */
public class HeroHeaderView extends RelativeLayout {

    private View mLogo;
    private View mTitle;
    private View mTips;

    private float mScreenWidth;
    private float mToolbarHeight;

    private float mLogoSizeExpanded;
    private float mLogoSizeCollapsed;
    private float mLogoMarginTopExpanded;
    private float mLogoMarginTopCollapsed;
    private float mLogoMarginStartCollapsed;

    private float mTitleScale;
    private float mTitleMarginTopExpanded;
    private float mTitleMarginStartCollapsed;

    private float mTipsMarginTopExpanded;

    public HeroHeaderView(Context context) {
        this(context, null);
    }

    public HeroHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeroHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.view_hero_header_layout, this);
        mLogo = findViewById(R.id.logo);
        mTitle = findViewById(R.id.title);
        mTips = findViewById(R.id.tips);
        mTitle.setPivotY(0);

        Resources res = context.getResources();
        mScreenWidth = UIUtils.getScreenWidth(context);
        mToolbarHeight = res.getDimension(R.dimen.toolbar_height);
        mLogoSizeExpanded = res.getDimension(R.dimen.hero_header_logo_size_expanded);
        mLogoSizeCollapsed = res.getDimension(R.dimen.hero_header_logo_size_collapsed);
        mLogoMarginTopExpanded = res.getDimension(R.dimen.hero_header_logo_margin_top_expanded);
        mLogoMarginTopCollapsed = res.getDimension(R.dimen.hero_header_logo_margin_top_collapsed);
        mLogoMarginStartCollapsed = res.getDimension(R.dimen.hero_header_logo_margin_start_collapsed);
        mTitleScale = 2F;
        mTitleMarginTopExpanded = res.getDimension(R.dimen.hero_header_title_margin_top_expanded);
        mTitleMarginStartCollapsed = res.getDimension(R.dimen.hero_header_title_margin_start_collapsed);
        mTipsMarginTopExpanded = res.getDimension(R.dimen.hero_header_tips_margin_top_expanded);
    }

    /**
     * @param percent [0, 1]
     */
    public void onCollapsing(float percent) {
        collapseLogo(percent);
        collapseTitle(percent);
        collapseTips(percent);
    }

    public void collapseLogo(float percent) {
        float size = mLogoSizeExpanded - percent * (mLogoSizeExpanded - mLogoSizeCollapsed);
        float marginTop = mLogoMarginTopExpanded - percent * (mLogoMarginTopExpanded - mLogoMarginTopCollapsed);
        float expandedMarginStart = (mScreenWidth - mLogo.getWidth()) / 2;
        float marginStart = expandedMarginStart - percent * (expandedMarginStart - mLogoMarginStartCollapsed);
        UIUtils.updateLayout(mLogo, (int) size, (int) size);
        UIUtils.updateLayoutMargin(mLogo, (int) marginStart, (int) marginTop, UIUtils.KEEP, UIUtils.KEEP);
    }

    public void collapseTitle(float percent) {
        float scale = mTitleScale - percent * (mTitleScale - 1);
        mTitle.setScaleX(scale);
        mTitle.setScaleY(scale);
        float collapsedMarginTop = (mToolbarHeight - mTitle.getHeight()) / 2;
        float marginTop = mTitleMarginTopExpanded - percent * (mTitleMarginTopExpanded - collapsedMarginTop);
        float expandedMarginStart = (mScreenWidth - mTitle.getWidth() * scale) / 2;
        float marginStart = expandedMarginStart - percent * (expandedMarginStart - mTitleMarginStartCollapsed);
        UIUtils.updateLayoutMargin(mTitle, (int) marginStart, (int) marginTop, UIUtils.KEEP, UIUtils.KEEP);
    }

    public void collapseTips(float percent) {
        mTips.setAlpha(1 - percent);
        float collapsedMarginTop = (mToolbarHeight - mTips.getHeight());
        float marginTop = mTipsMarginTopExpanded - percent * (mTipsMarginTopExpanded - collapsedMarginTop);
        UIUtils.updateLayoutMargin(mTips, UIUtils.KEEP, (int) marginTop, UIUtils.KEEP, UIUtils.KEEP);
    }

}
