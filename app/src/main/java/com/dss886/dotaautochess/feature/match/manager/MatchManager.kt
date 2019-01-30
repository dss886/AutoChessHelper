package com.dss886.dotaautochess.feature.match.manager

import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import java.util.*

/**
 * Created by dss886 on 2019/1/27.
 */
object MatchManager {

    private val mHeroList = ArrayList<Hero>()
    private val mChangeListeners = ArrayList<MatchChangeListener>()

    @Suppress("UNCHECKED_CAST")
    val heroList: List<Hero>
        get() = mHeroList.clone() as List<Hero>

    val speciesList: List<Pair<Species, Int>>
        get() = mHeroList.flatMap { it.speciesList.asIterable() }
                .groupBy { it }
                .map { Pair(it.key, it.value.size) }
                .sortedWith(BuffComparator())

    val professionList: List<Pair<Profession, Int>>
        get() = mHeroList.map { it.profession }
                .groupBy { it }
                .map { Pair(it.key, it.value.size) }
                .sortedWith(BuffComparator())

    init {
        for (i in 0..7) {
            mHeroList.add(Hero.values()[i])
        }
    }

    fun registerChangeListener(listener: MatchChangeListener) {
        mChangeListeners.add(listener)
    }

    fun unregisterChangeListener(listener: MatchChangeListener) {
        mChangeListeners.remove(listener)
    }

    private fun notifyListeners() {
        for (listener in mChangeListeners) {
            listener.onHeroesChanged()
        }
    }

    fun addHero(hero: Hero) {
        mHeroList.add(hero)
        notifyListeners()
    }

    fun removeHero(hero: Hero) {
        mHeroList.remove(hero)
        notifyListeners()
    }

}
