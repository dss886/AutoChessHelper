package com.dss886.dotaautochess.data

import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/25.
 */
enum class Hero constructor(var desc: String, var price: Price, var speciesList: List<Species>,
                            var profession: Profession, var ability: Ability, var iconRes: Int) {

    Axe("斧王", Price.GOLD_1, listOf(Species.Orc), Profession.Warrior, Ability.BerserkersCall, R.mipmap.hero_axe_icon),
    Enchantress("魅惑魔女", Price.GOLD_1, listOf(Species.Beast), Profession.Druid, Ability.NaturesAttendants, R.mipmap.hero_enchantress_icon),
    OgreMagi("食人魔魔法师", Price.GOLD_1, listOf(Species.Ogre), Profession.Mage, Ability.Bloodlust, R.mipmap.hero_ogre_magi_icon),
    Tusk("巨牙海民", Price.GOLD_1, listOf(Species.Beast), Profession.Warrior, Ability.WalrusPunch, R.mipmap.hero_tusk_icon),
    DrowRanger("卓尔游侠", Price.GOLD_1, listOf(Species.Undead), Profession.Hunter, Ability.PrecisionAura, R.mipmap.hero_drow_ranger_icon),
    BountyHunter("赏金猎人", Price.GOLD_1, listOf(Species.Goblin), Profession.Assassin, Ability.ShurikenToss, R.mipmap.hero_bounty_hunter_icon),
    Clockwerk("发条技师", Price.GOLD_1, listOf(Species.Goblin), Profession.Craftsman, Ability.BatteryAssault, R.mipmap.hero_clockwerk_icon),
    ShadowShaman("暗影萨满", Price.GOLD_1, listOf(Species.Troll), Profession.Shaman, Ability.HexSS, R.mipmap.hero_shadow_shaman_icon),
    Batrider("蝙蝠骑士", Price.GOLD_1, listOf(Species.Troll), Profession.Knight, Ability.StickyNapalm, R.mipmap.hero_batrider_icon),
    Tinker("修补匠", Price.GOLD_1, listOf(Species.Goblin), Profession.Craftsman, Ability.HeatSeekingMissile, R.mipmap.hero_tinker_icon),
    AntiMage("敌法师", Price.GOLD_1, listOf(Species.Elf), Profession.DemonHunter, Ability.ManaBreak, R.mipmap.hero_anti_mage_icon),

    CrystalMaiden("水晶室女", Price.GOLD_2, listOf(Species.Human), Profession.Mage, Ability.ArcaneAura, R.mipmap.hero_crystal_maiden_icon),
    Beastmaster("兽王", Price.GOLD_2, listOf(Species.Orc), Profession.Hunter, Ability.WildAxes, R.mipmap.hero_beastmaster_icon),
    Juggernaut("剑圣", Price.GOLD_2, listOf(Species.Orc), Profession.Warrior, Ability.BladeFury, R.mipmap.hero_juggernaut_icon),
    Timbersaw("伐木机", Price.GOLD_2, listOf(Species.Goblin), Profession.Craftsman, Ability.WhirlingDeath, R.mipmap.hero_timbersaw_icon),
    QueenOfPain("痛苦女王", Price.GOLD_2, listOf(Species.Demon), Profession.Assassin, Ability.ScreamOfPain, R.mipmap.hero_queen_of_pain_icon),
    Puck("精灵龙", Price.GOLD_2, listOf(Species.Elf, Species.Dragon), Profession.Mage, Ability.IllusoryOrb, R.mipmap.hero_puck_icon),
    WitchDocter("巫医", Price.GOLD_2, listOf(Species.Troll), Profession.Warlock, Ability.ParalyzingCask, R.mipmap.hero_witch_doctor_icon),
    Slardar("鱼人守卫", Price.GOLD_2, listOf(Species.Naga), Profession.Warrior, Ability.CorrosiveHaze, R.mipmap.hero_slardar_icon),
    ChaosKnight("混沌骑士", Price.GOLD_2, listOf(Species.Demon), Profession.Knight, Ability.ChaosBolt, R.mipmap.hero_chaos_knight_icon),
    TreantProtector("树精卫士", Price.GOLD_2, listOf(Species.Elf), Profession.Druid, Ability.LeechSeed, R.mipmap.hero_treant_protector_icon),
    Luna("月之骑士", Price.GOLD_2, listOf(Species.Elf), Profession.Knight, Ability.MoonGlaives, R.mipmap.hero_luna_icon),
    NaturesProphet("先知", Price.GOLD_2, listOf(Species.Elf), Profession.Druid, Ability.NaturesCall, R.mipmap.hero_natures_prophet_icon),

    Lycan("狼人", Price.GOLD_3, listOf(Species.Human, Species.Beast), Profession.Warrior, Ability.ShapeShift, R.mipmap.hero_lycan_icon),
    Venomancer("剧毒术士", Price.GOLD_3, listOf(Species.Beast), Profession.Warlock, Ability.PlagueWard, R.mipmap.hero_venomancer_icon),
    Ominknight("全能骑士", Price.GOLD_3, listOf(Species.Human), Profession.Knight, Ability.Purification, R.mipmap.hero_omniknight_icon),
    Razor("闪电幽魂", Price.GOLD_3, listOf(Species.Element), Profession.Mage, Ability.PlasmaField, R.mipmap.hero_razor_icon),
    Windranger("风行者", Price.GOLD_3, listOf(Species.Elf), Profession.Hunter, Ability.PowerShot, R.mipmap.hero_windranger_icon),
    PhantomAssassin("幻影刺客", Price.GOLD_3, listOf(Species.Elf), Profession.Assassin, Ability.CoupDeGrace, R.mipmap.hero_phantom_assassin_icon),
    Abaddon("死亡骑士", Price.GOLD_3, listOf(Species.Undead), Profession.Knight, Ability.AphoticShield, R.mipmap.hero_abaddon_icon),
    SandKing("沙王", Price.GOLD_3, listOf(Species.Beast), Profession.Assassin, Ability.BurrowStrike, R.mipmap.hero_sand_king_icon),
    Slark("鱼人夜行者", Price.GOLD_3, listOf(Species.Naga), Profession.Assassin, Ability.ShadowDance, R.mipmap.hero_slark_icon),
    Sniper("狙击手", Price.GOLD_3, listOf(Species.Dwarf), Profession.Hunter, Ability.Assassinate, R.mipmap.hero_sniper_icon),
    Viper("冥界亚龙", Price.GOLD_3, listOf(Species.Dragon), Profession.Assassin, Ability.ViperStrike, R.mipmap.hero_viper_icon),
    ShadowFiend("影魔", Price.GOLD_3, listOf(Species.Demon), Profession.Warlock, Ability.RequiemOfSouls, R.mipmap.hero_shadow_fiend_icon),
    Lina("秀逗魔导士", Price.GOLD_3, listOf(Species.Human), Profession.Mage, Ability.LagunaBlade, R.mipmap.hero_lina_icon),

    Doom("末日使者", Price.GOLD_4, listOf(Species.Demon), Profession.Warrior, Ability.Doom, R.mipmap.hero_doom_icon),
    Kunkka("海军上将", Price.GOLD_4, listOf(Species.Human), Profession.Warrior, Ability.GhostShip, R.mipmap.hero_kunkka_icon),
    TrollWarlord("巨魔战将", Price.GOLD_4, listOf(Species.Troll), Profession.Warrior, Ability.Fervor, R.mipmap.hero_troll_warlord_icon),
    KeeperOfTheLight("光之守卫", Price.GOLD_4, listOf(Species.Human), Profession.Mage, Ability.Illuminate, R.mipmap.hero_keeper_of_the_light_icon),
    Necrophs("死灵法师", Price.GOLD_4, listOf(Species.Undead), Profession.Warlock, Ability.DeathPulse, R.mipmap.hero_necrophos_icon),
    TemplarAssassin("圣堂刺客", Price.GOLD_4, listOf(Species.Elf), Profession.Assassin, Ability.Refraction, R.mipmap.hero_templar_assassin_icon),
    Alchemist("炼金术士", Price.GOLD_4, listOf(Species.Goblin), Profession.Warlock, Ability.AcidSpray, R.mipmap.hero_alchemist_icon),
    Disruptor("干扰者", Price.GOLD_4, listOf(Species.Orc), Profession.Shaman, Ability.StaticStorm, R.mipmap.hero_disruptor_icon),
    Medusa("蛇发女妖", Price.GOLD_4, listOf(Species.Naga), Profession.Hunter, Ability.StoneGaze, R.mipmap.hero_medusa_icon),
    LoneDruid("利爪德鲁伊", Price.GOLD_4, listOf(Species.Beast), Profession.Druid, Ability.ElderDragonForm, R.mipmap.hero_lone_druid_icon),
    DragonKnight("龙骑士", Price.GOLD_4, listOf(Species.Human, Species.Dragon), Profession.Knight, Ability.SummonSpiritBear, R.mipmap.hero_dragon_knight_icon),

    Gyrocopter("矮人直升机", Price.GOLD_5, listOf(Species.Dwarf), Profession.Craftsman, Ability.CallDown, R.mipmap.hero_gyrocopter_icon),
    Lich("巫妖", Price.GOLD_5, listOf(Species.Undead), Profession.Mage, Ability.ChainFrost, R.mipmap.hero_lich_icon),
    Tidehunter("潮汐猎人", Price.GOLD_5, listOf(Species.Naga), Profession.Hunter, Ability.Ravage, R.mipmap.hero_tidehunter_icon),
    Enigma("谜团", Price.GOLD_5, listOf(Species.Element), Profession.Warlock, Ability.Midnight, R.mipmap.hero_enigma_icon),
    Techies("地精工程师", Price.GOLD_5, listOf(Species.Goblin), Profession.Craftsman, Ability.RemoteMines, R.mipmap.hero_techies_icon)

}
