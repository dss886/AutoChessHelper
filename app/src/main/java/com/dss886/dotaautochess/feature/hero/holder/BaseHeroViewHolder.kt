package com.dss886.dotaautochess.feature.hero.holder

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dss886.dotaautochess.R
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.utils.loadImage
import com.dss886.dotaautochess.utils.toColor

/**
 * Created by dss886 on 2019/1/25.
 */
open class BaseHeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mHeroIcon: ImageView = itemView.findViewById(R.id.hero_icon)
    private val mName: TextView = itemView.findViewById(R.id.name)
    protected val mSpecies: TextView = itemView.findViewById(R.id.species)
    protected val mProfession: TextView = itemView.findViewById(R.id.profession)
    private val mAbilityIcon: ImageView = itemView.findViewById(R.id.ability_icon)
    private val mCost: TextView = itemView.findViewById(R.id.cost)

    open fun bind(hero: Hero, position: Int) {
        if (hero.speciesList.isEmpty()) {
            return
        }
        val context = mHeroIcon.context
        mHeroIcon.loadImage(hero.iconRes)
        mName.text = String.format("%s★", hero.desc)
        mName.setTextColor(hero.price.colorRes.toColor())
        mSpecies.text = buildSpeciesString(hero)
        mSpecies.setTextColor(hero.speciesList[0].colorRes.toColor())
        mProfession.text = hero.profession.desc
        mProfession.setTextColor(hero.profession.colorRes.toColor())
        mAbilityIcon.loadImage(hero.ability.iconRes)
        mCost.text = context.getString(R.string.hero_list_cost, hero.price.price)
    }

    protected open fun buildSpeciesString(hero: Hero): SpannableString {
        val content = hero.speciesList.joinToString(" ") { it.desc }
        val ss = SpannableString(content)
        var start = 0
        for (species in hero.speciesList) {
            ss.setSpan(ForegroundColorSpan(species.colorRes.toColor()), start,
                    start + species.desc.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            start += species.desc.length + 1
        }
        return ss
    }

}
