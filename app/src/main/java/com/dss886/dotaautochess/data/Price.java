package com.dss886.dotaautochess.data;

import com.dss886.dotaautochess.R;

/**
 * Created by dss886 on 2019/1/25.
 */
public enum Price {
    GOLD_1(1, R.color.palette_20),
    GOLD_2(2, R.color.palette_160),
    GOLD_3(3, R.color.palette_140),
    GOLD_4(4, R.color.palette_180),
    GOLD_5(5, R.color.palette_70);

    public int price;
    public int colorRes;

    Price(int price, int colorRes) {
        this.price = price;
        this.colorRes = colorRes;
    }
}
