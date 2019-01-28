package com.dss886.dotaautochess.feature.hero.holder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import com.dss886.dotaautochess.feature.hero.HeroAdapter;
import com.dss886.dotaautochess.utils.BuffUtils;
import com.dss886.dotaautochess.utils.RecyclerViewUtils;
import com.dss886.dotaautochess.utils.StringUtils;
import com.dss886.dotaautochess.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dss886 on 2019/1/25.
 */
public class HeroViewHolder extends RecyclerView.ViewHolder {

    private HeroAdapter mHeroAdapter;

    private View mTopLayout;
    private ImageView mHeroIcon;
    private TextView mName;
    private TextView mSpecies;
    private TextView mProfession;
    private ImageView mAbilityIcon;
    private TextView mCost;

    private View mExpandLayout;
    private TextView mBuffTitle1;
    private TextView mBuffContent1;
    private View mDivider1;
    private TextView mBuffTitle2;
    private TextView mBuffContent2;
    private View mDivider2;
    private TextView mBuffTitle3;
    private TextView mBuffContent3;

    private float mExpandViewHeight;

    public HeroViewHolder(HeroAdapter heroAdapter, @NonNull View itemView) {
        super(itemView);
        mHeroAdapter = heroAdapter;
        mTopLayout = itemView.findViewById(R.id.top_layout);
        mHeroIcon = itemView.findViewById(R.id.hero_icon);
        mName = itemView.findViewById(R.id.name);
        mSpecies = itemView.findViewById(R.id.species);
        mProfession = itemView.findViewById(R.id.profession);
        mAbilityIcon = itemView.findViewById(R.id.ability_icon);
        mCost = itemView.findViewById(R.id.cost);
        mExpandLayout = itemView.findViewById(R.id.expand_layout);
        mBuffTitle1 = itemView.findViewById(R.id.buff_title_1);
        mBuffContent1 = itemView.findViewById(R.id.buff_content_1);
        mDivider1 = itemView.findViewById(R.id.divider_1);
        mBuffTitle2 = itemView.findViewById(R.id.buff_title_2);
        mBuffContent2 = itemView.findViewById(R.id.buff_content_2);
        mDivider2 = itemView.findViewById(R.id.divider_2);
        mBuffTitle3 = itemView.findViewById(R.id.buff_title_3);
        mBuffContent3 = itemView.findViewById(R.id.buff_content_3);
    }

    public void bind(Hero hero, final int position, final boolean isExpanded) {
        if (hero.speciesList == null || hero.speciesList.length == 0 || hero.profession == null) {
            return;
        }
        Context context = mHeroIcon.getContext();
        Glide.with(context).load(hero.iconRes).into(mHeroIcon);
        mHeroIcon.setImageResource(hero.iconRes);
        mName.setText(String.format("%s★", hero.name));
        mName.setTextColor(ContextCompat.getColor(context, hero.price.colorRes));
        mSpecies.setText(buildSpeciesString(context, hero));
        mSpecies.setTextColor(ContextCompat.getColor(context, hero.speciesList[0].colorRes));
        mProfession.setText(hero.profession.name);
        mProfession.setTextColor(ContextCompat.getColor(context, hero.profession.colorRes));
        Glide.with(context).load(hero.ability.iconRes).into(mAbilityIcon);
        mCost.setText(context.getString(R.string.hero_list_cost, hero.price.price));
        mTopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeroAdapter.onItemExpandToggle(position);
            }
        });
        mExpandLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        mBuffTitle1.setText(hero.speciesList[0].buffName);
        mBuffTitle1.setTextColor(ContextCompat.getColor(context, hero.speciesList[0].colorRes));
        mBuffContent1.setText(BuffUtils.getBuffDescription(context, hero.speciesList[0]));
        if (hero.speciesList.length > 1) {
            mBuffTitle2.setVisibility(View.VISIBLE);
            mBuffContent2.setVisibility(View.VISIBLE);
            mDivider2.setVisibility(View.VISIBLE);
            mBuffTitle2.setText(hero.speciesList[1].buffName);
            mBuffTitle2.setTextColor(ContextCompat.getColor(context, hero.speciesList[1].colorRes));
            mBuffContent2.setText(BuffUtils.getBuffDescription(context, hero.speciesList[1]));
        } else {
            mBuffTitle2.setVisibility(View.GONE);
            mBuffContent2.setVisibility(View.GONE);
            mDivider2.setVisibility(View.GONE);
        }
        mBuffTitle3.setText(hero.profession.buffName);
        mBuffTitle3.setTextColor(ContextCompat.getColor(context, hero.profession.colorRes));
        mBuffContent3.setText(BuffUtils.getBuffDescription(context, hero.profession));
        // Measure the mExpandLayout to get height before rendering
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mExpandLayout.measure(widthMeasureSpec, heightMeasureSpec);
        mExpandViewHeight = mExpandLayout.getMeasuredHeight();
    }

    /**
     * Imitate the animation of RecyclerView's item removing.
     * Arguments of animation below are from DefaultItemAnimator.
     */
    public void doExpandOrCollapse(final boolean isExpand) {
        ObjectAnimator fade = ObjectAnimator.ofFloat(mExpandLayout, "alpha", isExpand ? 0f : 1f, isExpand ? 1f: 0f);
        fade.setDuration(120L);
        ValueAnimator move = ValueAnimator.ofFloat(isExpand ? 0f : 1f, isExpand ? 1f : 0f);
        move.setDuration(250L);
        move.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedValue() instanceof Float) {
                    float value = (float) animation.getAnimatedValue();
                    UIUtils.updateLayout(mExpandLayout, UIUtils.KEEP, (int) (mExpandViewHeight * value));
                }
            }
        });
        move.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (isExpand) {
                    mExpandLayout.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isExpand) {
                    mExpandLayout.setVisibility(View.GONE);
                }
                if (isExpand && itemView.getParent() instanceof RecyclerView) {
                    int offset = UIUtils.dp2px(itemView.getContext(), 56);
                    RecyclerViewUtils.scrollItemToCompletelyVisible((RecyclerView) itemView.getParent(), itemView, offset);
                }
            }
        });
        fade.start();
        move.start();
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
