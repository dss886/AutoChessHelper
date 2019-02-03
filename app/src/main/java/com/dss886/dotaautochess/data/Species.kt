package com.dss886.dotaautochess.data

import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/25.
 */
enum class Species constructor(override var desc: String, override var colorRes: Int, override var buffName: String, override var buffList: List<Buff>) : IBuffHolder {

    Human("人类", R.color.palette_10, "劝降", listOf(
            Buff(2, "所有友方人类攻击敌人有20%的概率使敌人缴械3秒。"),
            Buff(4, "所有友方人类攻击敌人有25%的概率使敌人缴械3秒。"),
            Buff(6, "所有友方人类攻击敌人有30%的概率使敌人缴械3秒。")
    )),
    Orc("兽人", R.color.palette_50, "强壮身躯", listOf(
            Buff(2, "所有友方兽人的最大生命值+250。"),
            Buff(4, "所有友方兽人的最大生命值+350。")
    )),
    Beast("野兽", R.color.palette_110, "野性之力", listOf(
            Buff(2, "所有友军的攻击力+10%，可以被召唤物继承。"),
            Buff(4, "所有友军的攻击力+15%，可以被召唤物继承。"),
            Buff(6, "所有友军的攻击力+20%，可以被召唤物继承。")
    )),
    Ogre("食人魔", R.color.palette_130, "高丽亚铁腕", listOf(
            Buff(1, "最大生命值+10%。")
    )),
    Undead("亡灵", R.color.palette_30, "恐怖", listOf(
            Buff(2, "使所有敌军的护甲-5。"),
            Buff(4, "使所有敌军的护甲-7。")
    )),
    Goblin("地精", R.color.palette_60, "活体装甲", listOf(
            Buff(3, "使一个随机友军的护甲+15，生命回复+10。"),
            Buff(6, "使所有友方地精的护甲+15，生命恢复+10。")
    )),
    Troll("巨魔", R.color.palette_100, "巫毒狂暴", listOf(
            Buff(2, "所有友方巨魔的攻击速度+35。"),
            Buff(4, "所有友军的攻击速度+35。")
    )),
    Elf("精灵", R.color.palette_140, "隐藏", listOf(
            Buff(3, "所有友方精灵有+25%几率闪避敌人的攻击。"),
            Buff(6, "所有友方精灵有+25%几率闪避敌人的攻击。")
    )),
    Demon("恶魔", R.color.palette_190, "邪能之力", listOf(
            Buff(1, "攻击会对敌人额外造成50%%的纯粹伤害。")
    )),
    Naga("娜迦", R.color.palette_150, "鳞甲", listOf(
            Buff(2, "使所有友军的魔法抗性+20。"),
            Buff(4, "使所有友军的魔法抗性+40。")
    )),
    Element("元素", R.color.palette_80, "大地灵气", listOf(
            Buff(2, "所有友方元素获得效果：被近战攻击的时候有30%概率使攻击者石化3秒。"),
            Buff(4, "所有友军获得效果：被近战攻击的时候有30%概率使攻击者石化3秒。")
    )),
    Dragon("龙", R.color.palette_50, "战吼", listOf(
            Buff(3, "所有友方龙的初始魔法值为100。")
    )),
    Dwarf("矮人", R.color.palette_65, "硬化氪金狗眼", listOf(
            Buff(1, "攻击距离+300。")
    ))

}
