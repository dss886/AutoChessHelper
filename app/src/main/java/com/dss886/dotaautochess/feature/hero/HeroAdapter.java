package com.dss886.dotaautochess.feature.hero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Hero;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
public class HeroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 1000;
    private static final int TYPE_FOOTER = 1001;
    private static final int TYPE_HERO = 1002;
    private static final String[] LEVELS = new String[]{"普通", "罕见", "稀有", "神话", "传说"};

    private static class DataWrapper {
        Object data;
        int type;

        DataWrapper(int type, Object data) {
            this.type = type;
            this.data = data;
        }

        @SuppressWarnings("unchecked")
        <T> T get(){
            return (T) data;
        }
    }

    private List<DataWrapper> mDataList = new ArrayList<>();

    HeroAdapter() {
        int currentLevel = 0;
        for (Hero hero : Hero.values()) {
            if (hero.price.price > currentLevel) {
                mDataList.add(new DataWrapper(TYPE_HEADER, LEVELS[currentLevel]));
                currentLevel++;
            }
            mDataList.add(new DataWrapper(TYPE_HERO, hero));
        }
        mDataList.add(new DataWrapper(TYPE_FOOTER, Hero.values().length));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.data_item_header, parent, false);
            return new HeaderHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.data_item_footer, parent, false);
            return new FooterHolder(view);
        } else {
            View view = inflater.inflate(R.layout.data_item_hero, parent, false);
            return new HeroViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataWrapper data = mDataList.get(position);
        if (holder instanceof HeaderHolder) {
            String level = data.get();
            ((HeaderHolder) holder).bind(level);
        } else if (holder instanceof FooterHolder) {
            int count = data.get();
            ((FooterHolder) holder).bind(count);
        } else if (holder instanceof HeroViewHolder) {
            Hero hero = data.get();
            ((HeroViewHolder) holder).bind(hero);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
