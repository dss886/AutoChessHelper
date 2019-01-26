package com.dss886.dotaautochess.data;

import com.dss886.dotaautochess.R;

/**
 * Created by dss886 on 2019/1/25.
 */
public enum Hero {

    Axe("斧王", Price.GOLD_1, new Species[]{Species.Orc}, Profession.Warrior, R.mipmap.hero_axe_icon),
    Enchantress("魅惑魔女", Price.GOLD_1, new Species[]{Species.Beast}, Profession.Druid, R.mipmap.hero_enchantress_icon),
    OgreMagi("食人魔魔法师", Price.GOLD_1, new Species[]{Species.Ogre}, Profession.Mage, R.mipmap.hero_ogre_magi_icon),
    Tusk("巨牙海民", Price.GOLD_1, new Species[]{Species.Beast}, Profession.Warrior, R.mipmap.hero_tusk_icon),
    DrowRanger("卓尔游侠", Price.GOLD_1, new Species[]{Species.Undead}, Profession.Hunter, R.mipmap.hero_drow_ranger_icon),
    BountyHunter("赏金猎人", Price.GOLD_1, new Species[]{Species.Goblin}, Profession.Assassin, R.mipmap.hero_bounty_hunter_icon),
    Clockwerk("发条技师", Price.GOLD_1, new Species[]{Species.Goblin}, Profession.Craftsman, R.mipmap.hero_clockwerk_icon),
    ShadowShaman("暗影萨满", Price.GOLD_1, new Species[]{Species.Troll}, Profession.Shaman, R.mipmap.hero_shadow_shaman_icon),
    Batrider("蝙蝠骑士", Price.GOLD_1, new Species[]{Species.Troll}, Profession.Knight, R.mipmap.hero_batrider_icon),
    Tinker("修补匠", Price.GOLD_1, new Species[]{Species.Goblin}, Profession.Craftsman, R.mipmap.hero_tinker_icon),
    AntiMage("敌法师", Price.GOLD_1, new Species[]{Species.Elf}, Profession.DemonHunter, R.mipmap.hero_anti_mage_icon),

    CrystalMaiden("水晶室女", Price.GOLD_2, new Species[]{Species.Human}, Profession.Mage, R.mipmap.hero_crystal_maiden_icon),
    Beastmaster("兽王", Price.GOLD_2, new Species[]{Species.Orc}, Profession.Hunter, R.mipmap.hero_beastmaster_icon),
    Juggernaut("剑圣", Price.GOLD_2, new Species[]{Species.Orc}, Profession.Warrior, R.mipmap.hero_juggernaut_icon),
    Timbersaw("伐木机", Price.GOLD_2, new Species[]{Species.Goblin}, Profession.Craftsman, R.mipmap.hero_timbersaw_icon),
    QueenOfPain("痛苦女王", Price.GOLD_2, new Species[]{Species.Demon}, Profession.Assassin, R.mipmap.hero_queen_of_pain_icon),
    Puck("精灵龙", Price.GOLD_2, new Species[]{Species.Elf, Species.Dragon}, Profession.Mage, R.mipmap.hero_puck_icon),
    WitchDocter("巫医", Price.GOLD_2, new Species[]{Species.Troll}, Profession.Warlock, R.mipmap.hero_witch_doctor_icon),
    Slardar("鱼人守卫", Price.GOLD_2, new Species[]{Species.Naga}, Profession.Warrior, R.mipmap.hero_slardar_icon),
    ChaosKnight("混沌骑士", Price.GOLD_2, new Species[]{Species.Demon}, Profession.Knight, R.mipmap.hero_chaos_knight_icon),
    TreantProtector("树精卫士", Price.GOLD_2, new Species[]{Species.Elf}, Profession.Druid, R.mipmap.hero_treant_protector_icon),
    NaturesProphet("先知", Price.GOLD_2, new Species[]{Species.Elf}, Profession.Druid, R.mipmap.hero_natures_prophet_icon),
    Luna("月之骑士", Price.GOLD_2, new Species[]{Species.Elf}, Profession.Knight, R.mipmap.hero_luna_icon),

    Lycan("狼人", Price.GOLD_3, new Species[]{Species.Human, Species.Beast}, Profession.Warrior, R.mipmap.hero_lycan_icon),
    Venomancer("剧毒术士", Price.GOLD_3, new Species[]{Species.Beast}, Profession.Warlock, R.mipmap.hero_venomancer_icon),
    Ominknight("全能骑士", Price.GOLD_3, new Species[]{Species.Human}, Profession.Knight, R.mipmap.hero_omniknight_icon),
    Razor("闪电幽魂", Price.GOLD_3, new Species[]{Species.Element}, Profession.Mage, R.mipmap.hero_razor_icon),
    Windranger("风行者", Price.GOLD_3, new Species[]{Species.Elf}, Profession.Hunter, R.mipmap.hero_windranger_icon),
    PhantomAssassin("幻影刺客", Price.GOLD_3, new Species[]{Species.Elf}, Profession.Assassin, R.mipmap.hero_phantom_assassin_icon),
    Abaddon("死亡骑士", Price.GOLD_3, new Species[]{Species.Undead}, Profession.Knight, R.mipmap.hero_abaddon_icon),
    SandKing("沙王", Price.GOLD_3, new Species[]{Species.Beast}, Profession.Assassin, R.mipmap.hero_sand_king_icon),
    Slark("鱼人夜行者", Price.GOLD_3, new Species[]{Species.Naga}, Profession.Assassin, R.mipmap.hero_slark_icon),
    Sniper("狙击手", Price.GOLD_3, new Species[]{Species.Dwarf}, Profession.Hunter, R.mipmap.hero_sniper_icon),
    Viper("冥界亚龙", Price.GOLD_3, new Species[]{Species.Dragon}, Profession.Assassin, R.mipmap.hero_viper_icon),
    ShadowFiend("影魔", Price.GOLD_3, new Species[]{Species.Demon}, Profession.Warlock, R.mipmap.hero_shadow_fiend_icon),
    Lina("秀逗魔导士", Price.GOLD_3, new Species[]{Species.Human}, Profession.Mage, R.mipmap.hero_lina_icon),

    Doom("末日使者", Price.GOLD_4, new Species[]{Species.Demon}, Profession.Warrior, R.mipmap.hero_doom_icon),
    Kunkka("海军上将", Price.GOLD_4, new Species[]{Species.Human}, Profession.Warrior, R.mipmap.hero_kunkka_icon),
    TrollWarlord("巨魔战将", Price.GOLD_4, new Species[]{Species.Troll}, Profession.Warrior, R.mipmap.hero_troll_warlord_icon),
    KeeperOfTheLight("光之守卫", Price.GOLD_4, new Species[]{Species.Human}, Profession.Mage, R.mipmap.hero_keeper_of_the_light_icon),
    Necrophs("死灵法师", Price.GOLD_4, new Species[]{Species.Undead}, Profession.Warlock, R.mipmap.hero_necrophos_icon),
    TemplarAssassin("圣堂刺客", Price.GOLD_4, new Species[]{Species.Elf}, Profession.Assassin, R.mipmap.hero_templar_assassin_icon),
    Alchemist("炼金术士", Price.GOLD_4, new Species[]{Species.Goblin}, Profession.Warlock, R.mipmap.hero_alchemist_icon),
    Disruptor("干扰者", Price.GOLD_4, new Species[]{Species.Orc}, Profession.Shaman, R.mipmap.hero_disruptor_icon),
    Medusa("蛇发女妖", Price.GOLD_4, new Species[]{Species.Naga}, Profession.Hunter, R.mipmap.hero_medusa_icon),
    LoneDruid("利爪德鲁伊", Price.GOLD_4, new Species[]{Species.Beast}, Profession.Druid, R.mipmap.hero_lone_druid_icon),
    DragonKnight("龙骑士", Price.GOLD_4, new Species[]{Species.Human, Species.Dragon}, Profession.Knight, R.mipmap.hero_dragon_knight_icon),

    Gyrocopter("矮人直升机", Price.GOLD_5, new Species[]{Species.Dwarf}, Profession.Craftsman, R.mipmap.hero_gyrocopter_icon),
    Lich("巫妖", Price.GOLD_5, new Species[]{Species.Undead}, Profession.Mage, R.mipmap.hero_lich_icon),
    Tidehunter("潮汐猎人", Price.GOLD_5, new Species[]{Species.Naga}, Profession.Hunter, R.mipmap.hero_tidehunter_icon),
    Enigma("谜团", Price.GOLD_5, new Species[]{Species.Element}, Profession.Warlock, R.mipmap.hero_enigma_icon),
    Techies("地精工程师", Price.GOLD_5, new Species[]{Species.Goblin}, Profession.Craftsman, R.mipmap.hero_techies_icon);

    public String name;
    public Price price;
    public Species[] speciesList;
    public Profession profession;
    public int iconRes;

    Hero(String name, Price price, Species[] speciesList, Profession profession, int iconRes) {
        this.name = name;
        this.price = price;
        this.speciesList = speciesList;
        this.profession = profession;
        this.iconRes = iconRes;
    }

}
