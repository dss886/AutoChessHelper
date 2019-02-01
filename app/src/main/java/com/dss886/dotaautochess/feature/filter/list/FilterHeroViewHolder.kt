package com.dss886.dotaautochess.feature.filter.list

import android.animation.ValueAnimator
import android.view.View
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.hero.holder.BaseHeroViewHolder
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import com.dss886.dotaautochess.utils.UIUtils
import com.dss886.dotaautochess.widget.AlertDialog

/**
 * Created by dss886 on 2019/1/25.
 */
class FilterHeroViewHolder(itemView: View) : BaseHeroViewHolder(itemView) {

    private val mContentLayout: View = itemView.findViewById(R.id.content_layout)
    private val mTagLayout: View = itemView.findViewById(R.id.tag_layout)
    private val mExitedText: View = itemView.findViewById(R.id.exited_text)

    override fun bind(hero: Hero, position: Int) {
        super.bind(hero, position)
        itemView.setOnClickListener {
            when {
                MatchManager.containHero(hero) -> return@setOnClickListener
                MatchManager.addHero(hero) -> {
                    doAddAnimation()
                }
                else -> showHeroPoolFullDialog()
            }
        }
        if (MatchManager.containHero(hero)) {
            mContentLayout.alpha = 0.38F
            UIUtils.setVisibility(mTagLayout, View.VISIBLE)
        } else {
            mContentLayout.alpha = 1F
            UIUtils.setVisibility(mTagLayout, View.GONE)
        }
    }

    private fun doAddAnimation() {
        mContentLayout.animate().alpha(0.38F).setDuration(120L).start()
        UIUtils.setVisibility(mTagLayout, View.VISIBLE)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        mExitedText.measure(widthMeasureSpec, heightMeasureSpec)
        val anim = ValueAnimator.ofInt(0, mExitedText.measuredWidth)
        anim.addUpdateListener { animator ->
            (animator.animatedValue as Int).let {
                UIUtils.updateLayout(mExitedText, it, UIUtils.KEEP)
            }
        }
        anim.duration = 150L
        anim.start()
    }

    private fun showHeroPoolFullDialog() {
        AlertDialog(itemView.context).apply {
            mTitle.text = itemView.context.getString(R.string.filter_hero_pool_full)
            mCancel.visibility = View.GONE
            mAccept.text = itemView.context.getString(R.string.common_dialog_btn_ok)
            mAccept.setOnClickListener { dismiss() }
            setCancelable(false)
        }.show()
    }

}
