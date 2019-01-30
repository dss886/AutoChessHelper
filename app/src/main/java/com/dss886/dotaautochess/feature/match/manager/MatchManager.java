package com.dss886.dotaautochess.feature.match.manager;

import com.dss886.dotaautochess.data.Hero;
import com.dss886.dotaautochess.data.Profession;
import com.dss886.dotaautochess.data.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

/**
 * Created by dss886 on 2019/1/27.
 */
public class MatchManager {

    private static MatchManager instance;

    public static MatchManager inst() {
        if (instance == null) {
            synchronized (MatchManager.class) {
                if (instance == null) {
                    instance = new MatchManager();
                }
            }
        }
        return instance;
    }

    @NonNull
    private final ArrayList<Hero> mHeroList = new ArrayList<>();
    @NonNull
    private final List<MatchChangeListener> mChangeListeners = new ArrayList<>();

    private MatchManager() {
        for (int i = 0; i < 8; i++) {
            mHeroList.add(Hero.values()[i]);
        }
    }

    public void registerChangeListener(MatchChangeListener listener) {
        mChangeListeners.add(listener);
    }

    public void unregisterChangeListener(MatchChangeListener listener) {
        mChangeListeners.remove(listener);
    }

    private void notifyListeners() {
        for (MatchChangeListener listener : mChangeListeners) {
            listener.onHeroesChanged();
        }
    }

    public void addHero(Hero hero) {
        mHeroList.add(hero);
        notifyListeners();
    }

    public void removeHero(Hero hero) {
        mHeroList.remove(hero);
        notifyListeners();
    }

    @NonNull
    public List<Hero> getHeroList() {
        //noinspection unchecked
        return (List<Hero>) mHeroList.clone();
    }

    public List<Pair<Species, Integer>> getSpeciesList() {
        List<Species> speciesList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (Hero hero : mHeroList) {
            for (Species species : hero.speciesList) {
                int index = speciesList.indexOf(species);
                if (index <= 0) {
                    speciesList.add(species);
                    countList.add(1);
                } else {
                    countList.set(index, countList.get(index) + 1);
                }
            }
        }
        List<Pair<Species, Integer>> result = new ArrayList<>();
        for (int i = 0; i < speciesList.size(); i++) {
            result.add(new Pair<>(speciesList.get(i), countList.get(i)));
        }
        Collections.sort(result, new BuffComparator());
        return result;
    }

    public List<Pair<Profession, Integer>> getProfessionList() {
        List<Profession> professionList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (Hero hero : mHeroList) {
            int index = professionList.indexOf(hero.profession);
            if (index <= 0) {
                professionList.add(hero.profession);
                countList.add(1);
            } else {
                countList.set(index, countList.get(index) + 1);
            }
        }
        List<Pair<Profession, Integer>> result = new ArrayList<>();
        for (int i = 0; i < professionList.size(); i++) {
            result.add(new Pair<>(professionList.get(i), countList.get(i)));
        }
        Collections.sort(result, new BuffComparator());
        return result;
    }

}
