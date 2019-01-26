package com.dss886.dotaautochess.data;

import com.dss886.dotaautochess.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dss886 on 2019/1/25.
 */
public enum Profession {

    Warrior("战士", R.color.palette_220, new ArrayList<Buff>() {{
        add(new Buff(3, "友方战士护甲+8"));
        add(new Buff(6, "友方战士护甲+10"));
    }}),
    Mage("法师", R.color.palette_125, new ArrayList<Buff>() {{
        add(new Buff(3, "所有地方魔抗-30"));
        add(new Buff(6, "所有地方魔抗-60"));
    }}),
    Hunter("猎人", R.color.palette_90, new ArrayList<Buff>() {{
        add(new Buff(3, "友方猎人攻击+25%"));
        add(new Buff(6, "友方猎人攻击+35%"));
    }}),
    Druid("德鲁伊", R.color.palette_70, new ArrayList<Buff>() {{
        add(new Buff(2, "2个★即可升级为★★"));
        add(new Buff(4, "2个★★即可升级为★★★"));
    }}),
    Assassin("刺客", R.color.palette_80, new ArrayList<Buff>() {{
        add(new Buff(3, "所有刺客四倍暴击几率+10%"));
        add(new Buff(6, "所有刺客四倍暴击几率+30%"));
    }}),
    Craftsman("工匠", R.color.palette_200, new ArrayList<Buff>() {{
        add(new Buff(2, "友方工匠生命恢复+10"));
        add(new Buff(4, "友方工匠生命恢复+20"));
    }}),
    Knight("骑士", R.color.palette_40, new ArrayList<Buff>() {{
        add(new Buff(2, "友方骑士有25%时间被减伤护盾覆盖"));
        add(new Buff(4, "友方骑士有35%时间被减伤护盾覆盖"));
        add(new Buff(6, "友方骑士有45%时间被减伤护盾覆盖"));
    }}),
    Shaman("萨满祭司", R.color.palette_210, new ArrayList<Buff>() {{
        add(new Buff(2, "战斗开始时将一个随机地方单位变羊6秒"));
    }}),
    DemonHunter("恶魔猎手", R.color.palette_190, new ArrayList<Buff>() {{
        add(new Buff(1, "视为敌方恶魔"));
    }}),
    Warlock("术士", R.color.palette_170, new ArrayList<Buff>() {{
        add(new Buff(3, "所有友军+20%吸血"));
        add(new Buff(6, "所有友军+30%吸血"));
    }});

    public String name;
    public int colorRes;
    List<Buff> mBuffList;

    Profession(String name, int colorRes, List<Buff> buffList) {
        this.name = name;
        this.colorRes = colorRes;
        this.mBuffList = buffList;
    }

}
