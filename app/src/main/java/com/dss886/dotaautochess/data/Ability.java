package com.dss886.dotaautochess.data;

import com.dss886.dotaautochess.R;

/**
 * Created by dss886 on 2019/1/26.
 */
public enum Ability {

    BerserkersCall("狂战士之吼", R.mipmap.ability_berserkers_call_icon),
    NaturesAttendants("自然之助", R.mipmap.ability_natures_attendants_icon),
    Bloodlust("嗜血术", R.mipmap.ability_bloodlust_icon),
    WalrusPunch("海象神拳！", R.mipmap.ability_walrus_punch_icon),
    PrecisionAura("射手天赋", R.mipmap.ability_precision_aura_icon),
    ShurikenToss("投掷飞镖", R.mipmap.ability_shuriken_toss_icon),
    BatteryAssault("弹幕冲击", R.mipmap.ability_battery_assault_icon),
    HexSS("妖术", R.mipmap.ability_hex_ss_icon),
    StickyNapalm("粘性燃油", R.mipmap.ability_sticky_napalm_icon),
    HeatSeekingMissile("热导飞弹", R.mipmap.ability_heat_seeking_missile_icon),
    ManaBreak("法力损毁", R.mipmap.ability_mana_break_icon),

    ArcaneAura("奥数光环", R.mipmap.ability_arcane_aura_icon),
    WildAxes("野性之斧", R.mipmap.ability_wild_axes_icon),
    BladeFury("剑刃风暴", R.mipmap.ability_blade_fury_icon),
    WhirlingDeath("死亡旋风", R.mipmap.ability_whirling_death_icon),
    ScreamOfPain("痛苦尖叫", R.mipmap.ability_scream_of_pain_icon),
    IllusoryOrb("幻象法球", R.mipmap.ability_illusory_orb_icon),
    ParalyzingCask("麻痹药剂", R.mipmap.ability_paralyzing_cask_icon),
    CorrosiveHaze("侵蚀雾霭", R.mipmap.ability_corrosive_haze_icon),
    ChaosBolt("混乱之箭", R.mipmap.ability_chaos_bolt_icon),
    LeechSeed("寄生种子", R.mipmap.ability_leech_seed_icon),
    MoonGlaives("月刃", R.mipmap.ability_moon_glaives_icon),
    NaturesCall("自然的呼唤", R.mipmap.ability_natures_call_icon),

    ShapeShift("变身", R.mipmap.ability_shapeshift_icon),
    PlagueWard("瘟疫守卫", R.mipmap.ability_plague_ward_icon),
    Purification("洗礼", R.mipmap.ability_purification_icon),
    PlasmaField("等离子场", R.mipmap.ability_plasma_field_icon),
    PowerShot("强力击", R.mipmap.ability_powershot_icon),
    CoupDeGrace("恩赐解脱", R.mipmap.ability_coup_de_grace_icon),
    AphoticShield("无光之盾", R.mipmap.ability_aphotic_shield_icon),
    BurrowStrike("掘地穿刺", R.mipmap.ability_burrowstrike_icon),
    ShadowDance("暗影之舞", R.mipmap.ability_shadow_dance_icon),
    Assassinate("暗杀", R.mipmap.ability_assassinate_icon),
    ViperStrike("蝮蛇突袭", R.mipmap.ability_viper_strike_icon),
    RequiemOfSouls("魂之挽歌", R.mipmap.ability_requiem_of_souls_icon),
    LagunaBlade("神灭斩", R.mipmap.ability_laguna_blade_icon),

    Doom("末日", R.mipmap.ability_doom_ability_icon),
    GhostShip("幽灵船", R.mipmap.ability_ghostship_icon),
    Fervor("热血战魂", R.mipmap.ability_fervor_icon),
    Illuminate("冲击波", R.mipmap.ability_illuminate_icon),
    DeathPulse("死亡脉冲", R.mipmap.ability_death_pulse_icon),
    Refraction("折光", R.mipmap.ability_refraction_icon),
    AcidSpray("酸性喷雾", R.mipmap.ability_acid_spray_icon),
    StaticStorm("静态风暴", R.mipmap.ability_static_storm_icon),
    StoneGaze("石化凝视", R.mipmap.ability_stone_gaze_icon),
    ElderDragonForm("古龙形态", R.mipmap.ability_elder_dragon_form_icon),
    SummonSpiritBear("熊灵伙伴", R.mipmap.ability_summon_spirit_bear_icon),

    CallDown("召唤飞弹", R.mipmap.ability_call_down_icon),
    ChainFrost("连环霜冻", R.mipmap.ability_chain_frost_icon),
    Ravage("毁灭", R.mipmap.ability_ravage_icon),
    Midnight("午夜凋零", R.mipmap.ability_midnight_pulse_icon),
    RemoteMines("自爆炸弹桶", R.mipmap.ability_remote_mines_icon);

    public String name;
    public int iconRes;

    Ability(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

}
