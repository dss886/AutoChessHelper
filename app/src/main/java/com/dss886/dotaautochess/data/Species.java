package com.dss886.dotaautochess.data;

import com.dss886.dotaautochess.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dss886 on 2019/1/25.
 */
public enum Species {

    Human("人类", R.color.palette_10,new ArrayList<Buff>() {{
        add(new Buff(2, "所有人类攻击时有20%几率缴械"));
        add(new Buff(4, "所有人类攻击时有45%几率缴械"));
        add(new Buff(6, "所有人类攻击时有75%几率缴械"));
    }}),
    Orc("兽人", R.color.palette_50,new ArrayList<Buff>(){{
        add(new Buff(2, "所有兽人生命+250"));
        add(new Buff(4, "所有兽人生命+350"));
    }}),
    Beast("野兽", R.color.palette_110,new ArrayList<Buff>(){{
        add(new Buff(2, "友军及召唤物攻击+15%"));
        add(new Buff(4, "友军及召唤物攻击+20%"));
        add(new Buff(6, "友军及召唤物攻击+25%"));
    }}),
    Ogre("食人魔", R.color.palette_130,new ArrayList<Buff>(){{
        add(new Buff(1, "最大生命值+10%"));
    }}),
    Undead("亡灵", R.color.palette_30,new ArrayList<Buff>(){{
        add(new Buff(2, "敌军护甲-5"));
        add(new Buff(4, "敌军护甲-7"));
    }}),
    Goblin("地精", R.color.palette_60,new ArrayList<Buff>(){{
        add(new Buff(3, "随机地精护甲+15，生命恢复+10"));
        add(new Buff(6, "所有地精护甲+15，生命恢复+10"));
    }}),
    Troll("巨魔", R.color.palette_100,new ArrayList<Buff>(){{
        add(new Buff(2, "友军巨魔攻速+30"));
        add(new Buff(4, "全体友军攻速+30"));
    }}),
    Elf("精灵", R.color.palette_140,new ArrayList<Buff>(){{
        add(new Buff(2, "友军精灵闪避+20%"));
        add(new Buff(4, "友军精灵闪避+40%"));
        add(new Buff(6, "友军精灵闪避+60%"));
    }}),
    Demon("恶魔", R.color.palette_190,new ArrayList<Buff>(){{
        // todo: check count of this skill
        add(new Buff(1, "自身攻击+50%"));
    }}),
    Naga("娜迦", R.color.palette_150,new ArrayList<Buff>(){{
        add(new Buff(2, "全体友军魔抗+20"));
        add(new Buff(4, "全体友军魔抗+40"));
    }}),
    Element("元素", R.color.palette_80,new ArrayList<Buff>(){{
        add(new Buff(2, "全体友军魔抗+30"));
    }}),
    Dragon("龙", R.color.palette_50,new ArrayList<Buff>(){{
        add(new Buff(3, "所有龙初始魔法值为100"));
    }}),
    Dwarf("矮人", R.color.palette_65,new ArrayList<Buff>() {{
        add(new Buff(1, "自身攻击距离+300"));
    }});

    public String name;
    public int colorRes;
    public List<Buff> mBuffList;

    Species(String name, int colorRes, List<Buff> buffList) {
        this.name = name;
        this.colorRes = colorRes;
        this.mBuffList = buffList;
    }

}
