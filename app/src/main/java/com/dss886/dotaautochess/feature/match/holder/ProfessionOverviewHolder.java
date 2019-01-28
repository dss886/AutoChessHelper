package com.dss886.dotaautochess.feature.match.holder;

import android.view.View;

import com.dss886.dotaautochess.data.Profession;
import com.dss886.dotaautochess.utils.CollectionUtils;

import androidx.annotation.NonNull;

/**
 * Created by dss886 on 2019/1/27.
 */
public class ProfessionOverviewHolder extends AbsOverviewHolder<Profession> {

    /**
     * R.layout.match_item_overview
     */
    public ProfessionOverviewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected boolean isBuffEnable(Profession profession, int count) {
        if (profession == null || CollectionUtils.isEmpty(profession.buffList)) {
            return false;
        }
        return count >= profession.buffList.get(0).count;
    }

    @Override
    String getItemName(Profession profession) {
        return profession != null ? profession.name : "";
    }

    @Override
    int getItemColorRes(Profession profession) {
        return profession != null ? profession.colorRes : -1;
    }
}
