package com.dss886.dotaautochess.feature.match.manager

import android.content.Context
import com.dss886.dotaautochess.app.App
import com.dss886.dotaautochess.data.Hero
import com.dss886.dotaautochess.data.Profession
import com.dss886.dotaautochess.data.Species
import java.util.*

/**
 * Created by dss886 on 2019/1/27.
 */
object MatchManager {

    private const val KEY_NAME = "hero"
    private val mSp = App.inst.getSharedPreferences("match", Context.MODE_PRIVATE)
    private val mHeroList = ArrayList<Hero>()
    private val mChangeListeners = ArrayList<MatchChangeListener>()

    val size: Int
        get() = mHeroList.size

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
        readData()
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

    fun addHero(hero: Hero): Boolean {
        if (!mHeroList.contains(hero) && mHeroList.size < 10) {
            mHeroList.add(hero)
            notifyListeners()
            saveData()
            return true
        }
        return false
    }

    fun containHero(hero: Hero): Boolean {
        return mHeroList.contains(hero)
    }

    fun removeHero(hero: Hero) {
        mHeroList.remove(hero)
        notifyListeners()
        saveData()
    }

    fun clearAllHero() {
        mHeroList.clear()
        notifyListeners()
        saveData()
    }

    private fun saveData() {
        val string = mHeroList.joinToString(",") { it.desc }
        mSp.edit().putString(KEY_NAME, string).apply()
    }

    private fun readData() {
        mSp.getString(KEY_NAME, "")
                ?.split(",")
                ?.forEach { desc ->
                    Hero.values().find { it.desc == desc }
                            ?.apply { mHeroList.add(this) }
                }
    }

}
