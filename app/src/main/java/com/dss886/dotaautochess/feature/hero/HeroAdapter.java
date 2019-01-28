package com.dss886.dotaautochess.feature.hero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Hero;
import com.dss886.dotaautochess.feature.hero.holder.FooterHolder;
import com.dss886.dotaautochess.feature.hero.holder.HeaderHolder;
import com.dss886.dotaautochess.feature.hero.holder.HeroViewHolder;

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

    private RecyclerView mRecyclerView;
    private List<DataWrapper> mDataList = new ArrayList<>();
    private int mCurrentExpandedPosition = -1;
    private long mLastExpandCollapseTime = 0L;

    HeroAdapter() {
        int currentLevel = 0;
        for (Hero hero : Hero.values()) {
            if (hero.price.price > currentLevel) {
                mDataList.add(new DataWrapper(TYPE_HEADER, hero.price.description));
                currentLevel++;
            }
            mDataList.add(new DataWrapper(TYPE_HERO, hero));
        }
        mDataList.add(new DataWrapper(TYPE_FOOTER, Hero.values().length));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.hero_item_header, parent, false);
            return new HeaderHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.hero_item_footer, parent, false);
            return new FooterHolder(view);
        } else {
            View view = inflater.inflate(R.layout.hero_item_hero, parent, false);
            return new HeroViewHolder(this, view);
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
            ((HeroViewHolder) holder).bind(hero, position, mCurrentExpandedPosition == position);
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

    /**
     * Only allow one item to expand.
     * Method notifyItemChange() has some issues on performance here,
     * so we have to expand it by do our own animations.
     */
    public void onItemExpandToggle(int position) {
        if (position < 0 || mRecyclerView == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - mLastExpandCollapseTime <= 300L) {
            return;
        }
        mLastExpandCollapseTime = now;

        boolean isExpand = mCurrentExpandedPosition != position;
        if (isExpand && mCurrentExpandedPosition >= 0) {
            RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(mCurrentExpandedPosition);
            if (holder instanceof HeroViewHolder) {
                ((HeroViewHolder) holder).doExpandOrCollapse(false);
            }
        }
        RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(position);
        if (holder instanceof HeroViewHolder) {
            ((HeroViewHolder) holder).doExpandOrCollapse(isExpand);
        }
        mCurrentExpandedPosition = isExpand ? position : -1;
    }
}
