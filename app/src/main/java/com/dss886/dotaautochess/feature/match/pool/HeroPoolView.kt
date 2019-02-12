package com.dss886.dotaautochess.feature.match.pool

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.filter.FilterActivity
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import com.dss886.dotaautochess.utils.Logger
import com.dss886.dotaautochess.utils.loadImage
import com.dss886.dotaautochess.utils.toColor
import com.dss886.dotaautochess.widget.AlertDialog
import com.dss886.dotaautochess.widget.SimpleGridLayout
import com.umeng.analytics.MobclickAgent

/**
 * Created by dss886 on 2019/1/27.
 */
class HeroPoolView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : SimpleGridLayout(context, attrs, defStyleAttr) {

    private var mHeroList = listOf<Hero>()
    private var mImageViewList = mutableListOf<ImageView>()

    init {
        for (i in 0..9) {
            val layout = LinearLayout(context)
            layout.setBackgroundColor(R.color.white_0a.toColor())
            val imageView = ImageView(context)
            mImageViewList.add(imageView)
            layout.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            addView(layout)
        }
    }

    fun bind(heroes: List<Hero>) {
        mHeroList = heroes
        for (i in mImageViewList.indices) {
            val imageView = mImageViewList[i]
            when {
                i < heroes.size -> bindHeroItem(imageView, heroes[i])
                i == heroes.size -> bindAddItem(imageView)
                else -> {
                    imageView.setImageDrawable(null)
                    imageView.setOnClickListener(null)
                }
            }
        }
    }

    private fun bindHeroItem(imageView: ImageView, hero: Hero) {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.loadImage(hero.iconRes)
        imageView.setOnClickListener {
            AlertDialog(imageView.context).apply {
                val ss = SpannableString(context.getString(R.string.match_remove_tip, hero.desc))
                ss.setSpan(ForegroundColorSpan(R.color.color_accent.toColor()), 3, 3 + hero.desc.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                mTitle.text = ss
                mCancel.text = context.getString(R.string.common_dialog_btn_cancel)
                mCancel.setOnClickListener { dismiss() }
                mAccept.text = context.getString(R.string.common_dialog_btn_remove)
                mAccept.setOnClickListener {
                    doRemoveHero(imageView, hero)
                    dismiss()
                    Logger.onEvent(context, "HeroPool", "remove")
                }
            }.show()
        }
    }

    private fun bindAddItem(imageView: ImageView) {
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.setImageResource(R.drawable.ic_add)
        imageView.setOnClickListener {
            context.startActivity(Intent(context, FilterActivity::class.java))
            Logger.onEvent(context, "HeroPool", "add")
        }
    }

    private fun doRemoveHero(imageView: ImageView, hero: Hero) {
        imageView.animate()
                .scaleX(0.8F)
                .scaleY(0.8F)
                .alpha(0F)
                .setDuration(200L)
                .setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        // A view has only one ViewPropertyAnimator,
                        // every time call view.animate() always return the same animator.
                        // If you don't remove its listener,
                        // it will leak and executing unexpected in some cases.
                        imageView.animate().setListener(null)
                        imageView.scaleX = 1F
                        imageView.scaleY = 1F
                        imageView.alpha = 1F
                        MatchManager.removeHero(hero)
                    }
                })
                .start()
    }

}
