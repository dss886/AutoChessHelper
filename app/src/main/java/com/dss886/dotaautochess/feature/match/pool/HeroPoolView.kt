package com.dss886.dotaautochess.feature.match.pool

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.feature.filter.FilterActivity
import com.dss886.dotaautochess.feature.match.manager.MatchManager
import com.dss886.dotaautochess.utils.loadImage
import com.dss886.dotaautochess.widget.SimpleGridLayout

/**
 * Created by dss886 on 2019/1/27.
 */
class HeroPoolView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : SimpleGridLayout(context, attrs, defStyleAttr) {

    private var mHeroList: List<Hero> = mutableListOf()

    init {
        for (i in 0..9) {
            val imageView = ImageView(context)
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.white_0a))
            addView(imageView)
        }
    }

    fun bind(heroes: List<Hero>) {
        mHeroList = heroes
        for (i in 0 until childCount) {
            val imageView = getChildAt(i) as ImageView
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
        imageView.setOnClickListener { MatchManager.removeHero(hero) }
    }

    private fun bindAddItem(imageView: ImageView) {
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.setImageResource(R.drawable.ic_add)
        imageView.setOnClickListener { context.startActivity(Intent(context, FilterActivity::class.java)) }
    }

}
