package com.dss886.dotaautochess.feature.match.holder;

import android.view.View;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Species;
import com.dss886.dotaautochess.utils.CollectionUtils;
import com.dss886.dotaautochess.utils.UIUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

/**
 * Created by dss886 on 2019/1/27.
 */
public class SpeciesOverviewHolder extends AbsOverviewHolder<Species> {

    private View mTitleView;

    /**
     * R.layout.match_item_overview
     */
    public SpeciesOverviewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = itemView.findViewById(R.id.title_view);
    }

    @Override
    public void bind(int titleRes, List<Pair<Species, Integer>> dataList) {
        super.bind(titleRes, dataList);
        int top = -UIUtils.dp2px(itemView.getContext(), 10);
        UIUtils.updateLayoutMargin(mTitleView, UIUtils.KEEP, top, UIUtils.KEEP, UIUtils.KEEP);
    }

    @Override
    protected boolean isBuffEnable(Species species, int count) {
        if (species == null || CollectionUtils.isEmpty(species.buffList)) {
            return false;
        }
        return count >= species.buffList.get(0).count;
    }

    @Override
    String getItemName(Species species) {
        return species != null ? species.name : "";
    }

    @Override
    int getItemColorRes(Species species) {
        return species != null ? species.colorRes : -1;
    }

}
