package com.dss886.dotaautochess.feature.match.holder;

import android.view.View;

import com.dss886.dotaautochess.data.Species;
import com.dss886.dotaautochess.utils.CollectionUtils;

import androidx.annotation.NonNull;

/**
 * Created by dss886 on 2019/1/27.
 */
public class SpeciesOverviewHolder extends AbsOverviewHolder<Species> {

    /**
     * R.layout.match_item_overview
     */
    public SpeciesOverviewHolder(@NonNull View itemView) {
        super(itemView);
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
