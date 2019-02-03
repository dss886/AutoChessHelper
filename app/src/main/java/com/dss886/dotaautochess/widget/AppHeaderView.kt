package com.dss886.dotaautochess.widget

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.feature.setting.SettingsActivity
import com.dss886.dotaautochess.utils.UIUtils

/**
 * Created by dss886 on 2019/1/26.
 */
class AppHeaderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mLogo: View
    private var mTitle: View
    private var mTips: View
    private var mSettings: View

    private var mScreenWidth: Float = 0F
    private var mToolbarHeight: Float = 0F

    private var mLogoSizeExpanded: Float = 0F
    private var mLogoSizeCollapsed: Float = 0F
    private var mLogoMarginTopExpanded: Float = 0F
    private var mLogoMarginTopCollapsed: Float = 0F
    private var mLogoMarginStartCollapsed: Float = 0F

    private var mTitleScale: Float = 0F
    private var mTitleMarginTopExpanded: Float = 0F
    private var mTitleMarginStartCollapsed: Float = 0F

    private var mTipsMarginTopExpanded: Float = 0F

    init {
        View.inflate(context, R.layout.widget_app_header_view, this)
        mLogo = findViewById(R.id.logo)
        mTitle = findViewById(R.id.title)
        mTips = findViewById(R.id.tips)
        mSettings = findViewById(R.id.settings)
        mTitle.pivotY = 0f

        val res = context.resources
        mScreenWidth = UIUtils.getScreenWidth(context).toFloat()
        mToolbarHeight = res.getDimension(R.dimen.toolbar_height)
        mLogoSizeExpanded = res.getDimension(R.dimen.hero_header_logo_size_expanded)
        mLogoSizeCollapsed = res.getDimension(R.dimen.hero_header_logo_size_collapsed)
        mLogoMarginTopExpanded = res.getDimension(R.dimen.hero_header_logo_margin_top_expanded)
        mLogoMarginTopCollapsed = res.getDimension(R.dimen.hero_header_logo_margin_top_collapsed)
        mLogoMarginStartCollapsed = res.getDimension(R.dimen.hero_header_logo_margin_start_collapsed)
        mTitleScale = 2f
        mTitleMarginTopExpanded = res.getDimension(R.dimen.hero_header_title_margin_top_expanded)
        mTitleMarginStartCollapsed = res.getDimension(R.dimen.hero_header_title_margin_start_collapsed)
        mTipsMarginTopExpanded = res.getDimension(R.dimen.hero_header_tips_margin_top_expanded)

        mSettings.setOnClickListener {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }

    /**
     * @param percent [0, 1]
     */
    fun onCollapsing(percent: Float) {
        collapseLogo(percent)
        collapseTitle(percent)
        collapseTips(percent)
    }

    private fun collapseLogo(percent: Float) {
        val size = mLogoSizeExpanded - percent * (mLogoSizeExpanded - mLogoSizeCollapsed)
        val marginTop = mLogoMarginTopExpanded - percent * (mLogoMarginTopExpanded - mLogoMarginTopCollapsed)
        val expandedMarginStart = (mScreenWidth - mLogo.width) / 2
        val marginStart = expandedMarginStart - percent * (expandedMarginStart - mLogoMarginStartCollapsed)
        UIUtils.updateLayout(mLogo, size.toInt(), size.toInt())
        UIUtils.updateLayoutMargin(mLogo, marginStart.toInt(), marginTop.toInt(), UIUtils.KEEP, UIUtils.KEEP)
    }

    private fun collapseTitle(percent: Float) {
        val scale = mTitleScale - percent * (mTitleScale - 1)
        mTitle.scaleX = scale
        mTitle.scaleY = scale
        val collapsedMarginTop = (mToolbarHeight - mTitle.height) / 2
        val marginTop = mTitleMarginTopExpanded - percent * (mTitleMarginTopExpanded - collapsedMarginTop)
        val expandedMarginStart = (mScreenWidth - mTitle.width * scale) / 2
        val marginStart = expandedMarginStart - percent * (expandedMarginStart - mTitleMarginStartCollapsed)
        UIUtils.updateLayoutMargin(mTitle, marginStart.toInt(), marginTop.toInt(), UIUtils.KEEP, UIUtils.KEEP)
    }

    private fun collapseTips(percent: Float) {
        mTips.alpha = 1 - percent
        val collapsedMarginTop = mToolbarHeight - mTips.height
        val marginTop = mTipsMarginTopExpanded - percent * (mTipsMarginTopExpanded - collapsedMarginTop)
        UIUtils.updateLayoutMargin(mTips, UIUtils.KEEP, marginTop.toInt(), UIUtils.KEEP, UIUtils.KEEP)
    }

}
