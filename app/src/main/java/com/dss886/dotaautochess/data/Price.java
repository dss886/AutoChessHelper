package com.dss886.dotaautochess.data;

import com.dss886.dotaautochess.R;

/**
 * Created by dss886 on 2019/1/25.
 */
public enum Price {

    GOLD_1(1, R.color.palette_20, "普通"),
    GOLD_2(2, R.color.palette_160, "罕见"),
    GOLD_3(3, R.color.palette_140, "稀有"),
    GOLD_4(4, R.color.palette_180, "神话"),
    GOLD_5(5, R.color.palette_70, "传说");

    public int price;
    public int colorRes;
    public String description;

    Price(int price, int colorRes, String description) {
        this.price = price;
        this.colorRes = colorRes;
        this.description = description;
    }
}
