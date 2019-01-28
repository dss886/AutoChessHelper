package com.dss886.dotaautochess.feature.match;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.IBuffHolder;
import com.dss886.dotaautochess.data.Profession;
import com.dss886.dotaautochess.data.Species;
import com.dss886.dotaautochess.feature.match.holder.BuffContentHolder;
import com.dss886.dotaautochess.feature.match.holder.BuffTitleHolder;
import com.dss886.dotaautochess.feature.match.holder.ProfessionOverviewHolder;
import com.dss886.dotaautochess.feature.match.holder.SpeciesOverviewHolder;
import com.dss886.dotaautochess.feature.match.manager.MatchManager;
import com.dss886.dotaautochess.utils.BuffUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SPECIES = 1000;
    private static final int TYPE_PROFESSION = 1001;
    private static final int TYPE_BUFF_TITLE = 1002;
    private static final int TYPE_BUFF_CONTENT = 1003;

    @NonNull private List<Pair<Species, Integer>> mSpeciesList;
    @NonNull private List<Pair<Profession, Integer>> mProfessionList;
    @NonNull private List<Pair<? extends IBuffHolder, Integer>> mMergedList;

    MatchAdapter() {
        mSpeciesList = MatchManager.inst().getSpeciesList();
        mProfessionList = MatchManager.inst().getProfessionList();
        List<Pair<? extends IBuffHolder, Integer>> enableList = new ArrayList<>();
        List<Pair<? extends IBuffHolder, Integer>> disableList = new ArrayList<>();
        for (Pair<Species, Integer> pair : mSpeciesList) {
            if (pair.first != null && pair.second != null) {
                if (BuffUtils.isBuffEnable(pair.first.buffList, pair.second)) {
                    enableList.add(pair);
                } else {
                    disableList.add(pair);
                }
            }
        }
        for (Pair<Profession, Integer> pair : mProfessionList) {
            if (pair.first != null && pair.second != null) {
                if (BuffUtils.isBuffEnable(pair.first.buffList, pair.second)) {
                    enableList.add(pair);
                } else {
                    disableList.add(pair);
                }
            }
        }
        mMergedList = new ArrayList<>();
        mMergedList.addAll(enableList);
        mMergedList.addAll(disableList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_SPECIES) {
            View view = inflater.inflate(R.layout.match_item_overview, parent, false);
            return new SpeciesOverviewHolder(view);
        } else if (viewType == TYPE_PROFESSION) {
            View view = inflater.inflate(R.layout.match_item_overview, parent, false);
            return new ProfessionOverviewHolder(view);
        } else if (viewType == TYPE_BUFF_TITLE) {
            View view = inflater.inflate(R.layout.match_item_buff_title, parent, false);
            return new BuffTitleHolder(view);
        } else {
            View view = inflater.inflate(R.layout.match_item_buff_content, parent, false);
            return new BuffContentHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SpeciesOverviewHolder) {
            ((SpeciesOverviewHolder) holder).bind(R.string.match_species_title, mSpeciesList);
        } else if (holder instanceof ProfessionOverviewHolder) {
            ((ProfessionOverviewHolder) holder).bind(R.string.match_profession_title, mProfessionList);
        } else if (holder instanceof BuffContentHolder) {
            Pair<? extends IBuffHolder, Integer> pair = mMergedList.get(position - 3);
            if (pair != null && pair.second != null) {
                ((BuffContentHolder) holder).bind(pair.first, pair.second, position == getItemCount() - 1);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_SPECIES;
            case 1:
                return TYPE_PROFESSION;
            case 2:
                return TYPE_BUFF_TITLE;
            default:
                return TYPE_BUFF_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return 3 + mSpeciesList.size() + mProfessionList.size();
    }

}
