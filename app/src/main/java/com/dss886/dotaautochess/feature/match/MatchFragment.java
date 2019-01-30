package com.dss886.dotaautochess.feature.match;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Hero;
import com.dss886.dotaautochess.feature.match.manager.MatchChangeListener;
import com.dss886.dotaautochess.feature.match.manager.MatchManager;
import com.dss886.dotaautochess.feature.match.pool.HeroPoolView;
import com.dss886.dotaautochess.utils.CollectionUtils;
import com.dss886.dotaautochess.utils.UIUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
public class MatchFragment extends Fragment implements MatchChangeListener {

    private RecyclerView mRecyclerView;
    private MatchAdapter mMatchAdapter;
    private HeroPoolView mHeroPoolView;
    private View mEmptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        mHeroPoolView = view.findViewById(R.id.hero_pool);
        mEmptyView = view.findViewById(R.id.empty_view);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mMatchAdapter = new MatchAdapter();
        mRecyclerView.setAdapter(mMatchAdapter);

        MatchManager.inst().registerChangeListener(this);
        onHeroesChanged();

        return view;
    }

    @Override
    public void onDestroyView() {
        MatchManager.inst().unregisterChangeListener(this);
        super.onDestroyView();
    }

    @Override
    public void onHeroesChanged() {
        List<Hero> heroList = MatchManager.inst().getHeroList();
        if (mHeroPoolView != null) {
            mHeroPoolView.bind(heroList);
        }
        if (mMatchAdapter != null) {
            mMatchAdapter.updateData();
        }
        if (CollectionUtils.isEmpty(heroList)) {
            UIUtils.setVisibility(mEmptyView, View.VISIBLE);
            UIUtils.setVisibility(mRecyclerView, View.GONE);
        } else {
            UIUtils.setVisibility(mEmptyView, View.GONE);
            UIUtils.setVisibility(mRecyclerView, View.VISIBLE);
        }
    }

}
