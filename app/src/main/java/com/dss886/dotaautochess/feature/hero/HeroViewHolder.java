package com.dss886.dotaautochess.feature.hero;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Hero;
import com.dss886.dotaautochess.data.Species;
import com.dss886.dotaautochess.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
class HeroViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private ImageView mHeroIcon;
    private TextView mSpecies;
    private TextView mProfession;
    private TextView mCost;

    HeroViewHolder(@NonNull View itemView) {
        super(itemView);
        mHeroIcon = itemView.findViewById(R.id.hero_icon);
        mName = itemView.findViewById(R.id.name);
        mSpecies = itemView.findViewById(R.id.species);
        mProfession = itemView.findViewById(R.id.profession);
        mCost = itemView.findViewById(R.id.cost);
    }

    void bind(Hero hero) {
        Context context = mHeroIcon.getContext();
        Glide.with(context).load(hero.iconRes).into(mHeroIcon);
        mHeroIcon.setImageResource(hero.iconRes);
        mName.setText(String.format("%s★", hero.name));
        mName.setTextColor(ContextCompat.getColor(context, hero.price.colorRes));
        mSpecies.setText(buildSpeciesString(context, hero));
        mSpecies.setTextColor(ContextCompat.getColor(context, hero.speciesList[0].colorRes));
        mProfession.setText(hero.profession.name);
        mProfession.setTextColor(ContextCompat.getColor(context, hero.profession.colorRes));
        mCost.setText(context.getString(R.string.hero_list_cost, hero.price.price));
    }

    private SpannableString buildSpeciesString(Context context, Hero hero) {
        if (hero == null || hero.speciesList == null) {
            return null;
        }
        String content = StringUtils.join(" ", getSpeciesNameList(hero.speciesList));
        SpannableString ss = new SpannableString(content);
        int start = 0;
        for (Species species : hero.speciesList) {
            int color = ContextCompat.getColor(context, species.colorRes);
            ss.setSpan(new ForegroundColorSpan(color), start,
                    start + species.name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            start += species.name.length() + 1;
        }
        return ss;
    }

    private List<String> getSpeciesNameList(Species[] speciesList) {
        List<String> nameList = new ArrayList<>();
        if (speciesList == null) {
            return nameList;
        }
        for (Species species : speciesList) {
            nameList.add(species.name);
        }
        return nameList;
    }


}
