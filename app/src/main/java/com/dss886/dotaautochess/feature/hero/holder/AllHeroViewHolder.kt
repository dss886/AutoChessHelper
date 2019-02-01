package com.dss886.dotaautochess.feature.hero.holder

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.hero.IHeroItemCallback
import com.dss886.dotaautochess.utils.BuffUtils
import com.dss886.dotaautochess.utils.RecyclerViewUtils
import com.dss886.dotaautochess.utils.UIUtils
import com.dss886.dotaautochess.utils.dpInt

/**
 * Created by dss886 on 2019/1/25.
 */
class AllHeroViewHolder(itemView: View, private val callback: IHeroItemCallback? = null)
    : BaseHeroViewHolder(itemView) {

    private val mTopLayout: View = itemView.findViewById(R.id.top_layout)
    private val mExpandLayout: View = itemView.findViewById(R.id.expand_layout)
    private val mBuffTitle1: TextView = itemView.findViewById(R.id.buff_title_1)
    private val mBuffContent1: TextView = itemView.findViewById(R.id.buff_content_1)
    private val mBuffTitle2: TextView = itemView.findViewById(R.id.buff_title_2)
    private val mBuffContent2: TextView = itemView.findViewById(R.id.buff_content_2)
    private val mDivider2: View = itemView.findViewById(R.id.divider_2)
    private val mBuffTitle3: TextView = itemView.findViewById(R.id.buff_title_3)
    private val mBuffContent3: TextView = itemView.findViewById(R.id.buff_content_3)

    private var mExpandViewHeight: Float = 0.toFloat()

    fun bind(hero: Hero, position: Int, isExpanded: Boolean) {
        super.bind(hero, position)
        val context = mTopLayout.context
        mTopLayout.setOnClickListener { callback?.onItemClick(position, hero) }

        mExpandLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        mBuffTitle1.text = hero.speciesList[0].buffName
        mBuffTitle1.setTextColor(ContextCompat.getColor(context, hero.speciesList[0].colorRes))
        mBuffContent1.text = BuffUtils.getBuffDescription(context, hero.speciesList[0])
        if (hero.speciesList.size > 1) {
            mBuffTitle2.visibility = View.VISIBLE
            mBuffContent2.visibility = View.VISIBLE
            mDivider2.visibility = View.VISIBLE
            mBuffTitle2.text = hero.speciesList[1].buffName
            mBuffTitle2.setTextColor(ContextCompat.getColor(context, hero.speciesList[1].colorRes))
            mBuffContent2.text = BuffUtils.getBuffDescription(context, hero.speciesList[1])
        } else {
            mBuffTitle2.visibility = View.GONE
            mBuffContent2.visibility = View.GONE
            mDivider2.visibility = View.GONE
        }
        mBuffTitle3.text = hero.profession.buffName
        mBuffTitle3.setTextColor(ContextCompat.getColor(context, hero.profession.colorRes))
        mBuffContent3.text = BuffUtils.getBuffDescription(context, hero.profession)
        // Measure the mExpandLayout to get height before rendering
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        mExpandLayout.measure(widthMeasureSpec, heightMeasureSpec)
        mExpandViewHeight = mExpandLayout.measuredHeight.toFloat()
    }

    /**
     * Imitate the animation of RecyclerView's item removing.
     * Arguments of animation below are from DefaultItemAnimator.
     */
    fun doExpandOrCollapse(isExpand: Boolean) {
        val fade = ObjectAnimator.ofFloat(mExpandLayout, "alpha", if (isExpand) 0f else 1f, if (isExpand) 1f else 0f)
        fade.duration = 120L
        val move = ValueAnimator.ofFloat(if (isExpand) 0f else 1f, if (isExpand) 1f else 0f)
        move.duration = 250L
        move.addUpdateListener { animation ->
            if (animation.animatedValue is Float) {
                val value = animation.animatedValue as Float
                UIUtils.updateLayout(mExpandLayout, UIUtils.KEEP, (mExpandViewHeight * value).toInt())
            }
        }
        move.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                if (isExpand) {
                    mExpandLayout.visibility = View.VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animator) {
                if (!isExpand) {
                    mExpandLayout.visibility = View.GONE
                }
                if (isExpand && itemView.parent is RecyclerView) {
                    RecyclerViewUtils.scrollItemToCompletelyVisible(itemView.parent as RecyclerView, itemView, 56.dpInt)
                }
            }
        })
        fade.start()
        move.start()
    }

}
