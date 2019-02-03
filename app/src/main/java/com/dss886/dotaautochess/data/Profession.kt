package com.dss886.dotaautochess.data

import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/25.
 */
enum class Profession constructor(override var desc: String, override var colorRes: Int, override var buffName: String, override var buffList: List<Buff>) : IBuffHolder {

    Warrior("战士", R.color.palette_220, "铜皮铁甲", listOf(
            Buff(3, "所有友方战士的护甲+7。"),
            Buff(6, "所有友方战士的护甲+8。"),
            Buff(9, "所有友方战士的护甲+9。")
    )),
    Mage("法师", R.color.palette_125, "法术易伤", listOf(
            Buff(3, "使所有敌军的魔法抗性-50。"),
            Buff(6, "使所有敌军的魔法抗性-30。")
    )),
    Hunter("猎人", R.color.palette_90, "致命射击", listOf(
            Buff(3, "所有友方猎人的攻击力+30%。"),
            Buff(6, "所有友方猎人的攻击力+30%。")
    )),
    Druid("德鲁伊", R.color.palette_70, "野性成长", listOf(
            Buff(2, "场上有2个相同的★德鲁伊就可以升级为★★德鲁伊。"),
            Buff(4, "场上有2个相同的2个★★德鲁伊就可以升级为★★★德鲁伊。")
    )),
    Assassin("刺客", R.color.palette_80, "致命一击", listOf(
            Buff(3, "所有友方刺客的攻击有+10%概率造成4倍伤害。"),
            Buff(6, "所有友方刺客的攻击有+20%概率造成4倍伤害。")
    )),
    Craftsman("工匠", R.color.palette_200, "自我修复", listOf(
            Buff(2, "所有友方工匠的生命回复速度+15。"),
            Buff(4, "所有友方工匠的生命回复速度+25。")
    )),
    Knight("骑士", R.color.palette_40, "王者祝福", listOf(
            Buff(2, "所有友方骑士有+25%时间被减伤护盾庇护。"),
            Buff(4, "所有友方骑士有+35%时间被减伤护盾庇护。"),
            Buff(6, "所有友方骑士有+45%时间被减伤护盾庇护。")
    )),
    Shaman("萨满祭司", R.color.palette_210, "妖术", listOf(
            Buff(2, "战斗开始时将一个随机敌方棋子变形成青蛙，持续6秒。")
    )),
    DemonHunter("恶魔猎手", R.color.palette_190, "破碎灵魂", listOf(
            Buff(1, "此棋子视为敌方的一个恶魔。"),
            Buff(2, "所有友方恶魔视为同一种类。")
    )),
    Warlock("术士", R.color.palette_170, "灵魂榨取", listOf(
            Buff(3, "使所有友军的攻击20%吸血。"),
            Buff(6, "使所有友军的攻击30%吸血。")
    ))

}
