package com.dss886.dotaautochess.data;

import android.content.Context;

import com.dss886.dotaautochess.R;
import com.dss886.dotaautochess.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dss886 on 2019/1/25.
 */
public enum Species {

    Human("人类", R.color.palette_10, "劝降", new ArrayList<Buff>() {{
        add(new Buff(2, "所有友方人类攻击敌人有20%的概率使敌人缴械3秒。"));
        add(new Buff(4, "所有友方人类攻击敌人有45%的概率使敌人缴械3秒。"));
        add(new Buff(6, "所有友方人类攻击敌人有75%的概率使敌人缴械3秒。"));
    }}),
    Orc("兽人", R.color.palette_50, "强壮身躯", new ArrayList<Buff>(){{
        add(new Buff(2, "所有友方兽人的最大生命值+250。"));
        add(new Buff(4, "所有友方兽人的最大生命值+350。"));
    }}),
    Beast("野兽", R.color.palette_110, "野性之力", new ArrayList<Buff>(){{
        add(new Buff(2, "所有友军的攻击力+15%，可以被召唤物继承。"));
        add(new Buff(4, "所有友军的攻击力+20%，可以被召唤物继承。"));
        add(new Buff(6, "所有友军的攻击力+25%，可以被召唤物继承。"));
    }}),
    Ogre("食人魔", R.color.palette_130, "高丽亚铁腕", new ArrayList<Buff>(){{
        add(new Buff(1, "最大生命值+10%。"));
    }}),
    Undead("亡灵", R.color.palette_30, "恐怖", new ArrayList<Buff>(){{
        add(new Buff(2, "使所有敌军的护甲-5。"));
        add(new Buff(4, "使所有敌军的护甲-7。"));
    }}),
    Goblin("地精", R.color.palette_60, "活体装甲", new ArrayList<Buff>(){{
        add(new Buff(3, "使一个随机友方军的护甲+15，生命回复+10。"));
        add(new Buff(6, "所有地精护甲+15，生命恢复+10。"));
    }}),
    Troll("巨魔", R.color.palette_100, "巫毒狂暴", new ArrayList<Buff>(){{
        add(new Buff(2, "所有友方巨魔的攻击速度+30。"));
        add(new Buff(4, "所有友军的攻击速度+30。"));
    }}),
    Elf("精灵", R.color.palette_140, "隐藏", new ArrayList<Buff>(){{
        add(new Buff(2, "所有友方精灵有+20%几率闪避敌人的攻击。"));
        add(new Buff(4, "所有友方精灵有+40%几率闪避敌人的攻击。"));
        add(new Buff(6, "所有友方精灵有+60%几率闪避敌人的攻击。"));
    }}),
    Demon("恶魔", R.color.palette_190, "邪能之力", new ArrayList<Buff>(){{
        // todo: check count of this skill
        add(new Buff(1, "攻击时对敌人额外造成50%的纯粹伤害。"));
    }}),
    Naga("娜迦", R.color.palette_150, "鳞甲", new ArrayList<Buff>(){{
        add(new Buff(2, "使所有友军的魔法抗性+20。"));
        add(new Buff(4, "使所有友军的魔法抗性+40。"));
    }}),
    Element("元素", R.color.palette_80, "大地灵气", new ArrayList<Buff>(){{
        add(new Buff(2, "所有友方元素被近战攻击时有30%概率使攻击者石化3秒。"));
        add(new Buff(4, "全体友军魔抗+30。"));
    }}),
    Dragon("龙", R.color.palette_50, "战吼", new ArrayList<Buff>(){{
        add(new Buff(3, "所有友方龙的初始魔法值为100。"));
    }}),
    Dwarf("矮人", R.color.palette_65, "硬化氪金狗眼", new ArrayList<Buff>() {{
        add(new Buff(1, "攻击距离+300。"));
    }});

    public String name;
    public int colorRes;
    public String buffName;
    public List<Buff> buffList;

    Species(String name, int colorRes, String buffName, List<Buff> buffList) {
        this.name = name;
        this.colorRes = colorRes;
        this.buffName = buffName;
        this.buffList = buffList;
    }

    public String getBuffDescription(Context context) {
        List<String> descList = new ArrayList<>();
        if (buffList != null) {
            for (Buff buff : buffList) {
                descList.add(context.getString(R.string.data_buff_content, buff.count, name, buff.description));
            }
        }
        return StringUtils.join("\n", descList);
    }

}
