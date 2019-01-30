package com.dss886.dotaautochess.feature.match.pool;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.data.Hero;
import com.dss886.dotaautochess.feature.filter.FilterActivity;
import com.dss886.dotaautochess.feature.match.manager.MatchManager;
import com.dss886.dotaautochess.widget.SimpleGridLayout;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Created by dss886 on 2019/1/27.
 */
public class HeroPoolView extends SimpleGridLayout {

    private List<Hero> mHeroList;

    public HeroPoolView(Context context) {
        this(context, null);
    }

    public HeroPoolView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeroPoolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.white_08));
            addView(imageView);
        }
    }

    public void bind(List<Hero> heroes) {
        mHeroList = heroes;
        for (int i = 0; i < getChildCount(); i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (i < heroes.size()) {
                bindHeroItem(imageView, heroes.get(i));
            } else if (i == heroes.size()) {
                bindAddItem(imageView);
            } else {
                imageView.setImageDrawable(null);
                imageView.setOnClickListener(null);
            }
        }
    }

    private void bindHeroItem(ImageView imageView, final Hero hero) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(getContext()).load(hero.iconRes).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchManager.inst().removeHero(hero);
            }
        });
    }

    private void bindAddItem(ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.drawable.ic_add);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), FilterActivity.class));
            }
        });
    }

}
