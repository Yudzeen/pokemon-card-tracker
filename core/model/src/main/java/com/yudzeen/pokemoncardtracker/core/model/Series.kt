package com.yudzeen.pokemoncardtracker.core.model

enum class Series {
    Original,
    Neo,
    LegendaryCollection,
    ECard,
    EX,
    DiamondPearl,
    Platinum,
    HeartGoldSoulSilver,
    BlackWhite,
    XY,
    SunMoon,
    SwordShield,
    ScarletViolet,
    MegaEvolution;

    enum class Expansion(val series: Series) {
        // Original
        Base(Original),
        Jungle(Original),
        Fossil(Original),
        Base2(Original),
        TeamRocket(Original),
        GymHeroes(Original),
        GymChallenge(Original),

        // Neo
        NeoGenesis(Neo),
        NeoDiscovery(Neo),
        NeoRevelation(Neo),
        NeoDestiny(Neo),

        // LegendaryCollection
        LegendaryCollection(Series.LegendaryCollection),

        // ECard
        Expedition(ECard),
        Aquapolis(ECard),
        Skyridge(ECard),

        // EX
        RubySapphire(EX),
        Sandstorm(EX),
        Dragon(EX),
        TeamMagmaVsTeamAqua(EX),
        HiddenLegends(EX),
        FireRedLeafGreen(EX),
        TeamRocketReturns(EX),
        Deoxys(EX),
        Emerald(EX),
        UnseenForces(EX),
        DeltaSpecies(EX),
        LegendMaker(EX),
        HolonPhantoms(EX),
        CrystalGuardians(EX),
        DragonFrontiers(EX),
        PowerKeepers(EX),

        // DiamondPearl
        DiamondPearl(Series.DiamondPearl),
        MysteriousTreasures(Series.DiamondPearl),
        SecretWonders(Series.DiamondPearl),
        GreatEncounters(Series.DiamondPearl),
        MajesticDawn(Series.DiamondPearl),
        LegendsAwakened(Series.DiamondPearl),
        Stormfront(Series.DiamondPearl),

        // Platinum
        Platinum(Series.Platinum),
        RisingRivals(Series.Platinum),
        SupremeVictors(Series.Platinum),
        Arceus(Series.Platinum),

        // HeartGoldSoulSilver
        HeartGoldSoulSilver(Series.HeartGoldSoulSilver),
        Unleashed(Series.HeartGoldSoulSilver),
        Undaunted(Series.HeartGoldSoulSilver),
        Triumphant(Series.HeartGoldSoulSilver),
        CallOfLegends(Series.HeartGoldSoulSilver),

        // BlackWhite
        BlackWhite(Series.BlackWhite),
        EmergingPowers(Series.BlackWhite),
        NobleVictories(Series.BlackWhite),
        NextDestinies(Series.BlackWhite),
        DarkExplorers(Series.BlackWhite),
        DragonsExalted(Series.BlackWhite),
        DragonVault(Series.BlackWhite),
        BoundariesCrossed(Series.BlackWhite),
        PlasmaStorm(Series.BlackWhite),
        PlasmaFreeze(Series.BlackWhite),
        PlasmaBlast(Series.BlackWhite),
        LegendaryTreasures(Series.BlackWhite),

        // XY
        KalosStarterSet(Series.XY),
        XY(Series.XY),
        Flashfire(Series.XY),
        FuriousFists(Series.XY),
        PhantomForces(Series.XY),
        PrimalClash(Series.XY),
        DoubleCrisis(Series.XY),
        RoaringSkies(Series.XY),
        AncientOrigins(Series.XY),
        Breakthrough(Series.XY),
        Breakpoint(Series.XY),
        Generations(Series.XY),
        FatesCollide(Series.XY),
        SteamSiege(Series.XY),
        Evolutions(Series.XY),

        // SunMoon
        SunMoon(Series.SunMoon),
        GuardiansRising(Series.SunMoon),
        BurningShadows(Series.SunMoon),
        ShiningLegends(Series.SunMoon),
        CrimsonInvasion(Series.SunMoon),
        UltraPrism(Series.SunMoon),
        ForbiddenLight(Series.SunMoon),
        CelestialStorm(Series.SunMoon),
        DragonMajesty(Series.SunMoon),
        LostThunder(Series.SunMoon),
        TeamUp(Series.SunMoon),
        DetectivePikachu(Series.SunMoon),
        UnbrokenBonds(Series.SunMoon),
        UnifiedMinds(Series.SunMoon),
        HiddenFates(Series.SunMoon),
        CosmicEclipse(Series.SunMoon),

        // SwordShield
        SwordShield(Series.SwordShield),
        RebelClash(Series.SwordShield),
        DarknessAblaze(Series.SwordShield),
        ChampionsPath(Series.SwordShield),
        VividVoltage(Series.SwordShield),
        ShiningFates(Series.SwordShield),
        ChillingReign(Series.SwordShield),
        EvolvingSkies(Series.SwordShield),
        Celebrations(Series.SwordShield),
        FusionStrike(Series.SwordShield),
        BrilliantStars(Series.SwordShield),
        AstralRadiance(Series.SwordShield),
        PokemonGo(Series.SwordShield),
        LostOrigin(Series.SwordShield),
        SilverTempest(Series.SwordShield),
        CrownZenith(Series.SwordShield),

        // ScarletViolet
        ScarletViolet(Series.ScarletViolet),
        PaldeaEvolved(Series.ScarletViolet),
        ObsidianFlames(Series.ScarletViolet),
        SV151(Series.ScarletViolet),
        ParadoxRift(Series.ScarletViolet),
        PaldeanFates(Series.ScarletViolet),
        TemporalForces(Series.ScarletViolet),
        TwilightMasquerade(Series.ScarletViolet),
        ShroudedFable(Series.ScarletViolet),
        StellarCrown(Series.ScarletViolet),
        SurgingSparks(Series.ScarletViolet),
        PrismaticEvolutions(Series.ScarletViolet),
        JourneyTogether(Series.ScarletViolet),
        DestinedRivals(Series.ScarletViolet),
        BlackBolt(Series.ScarletViolet),
        WhiteFlare(Series.ScarletViolet),

        // MegaEvolution
        MegaEvolution(Series.MegaEvolution),
        PhantasmalFlames(Series.MegaEvolution),
        AscendedHeroes(Series.MegaEvolution),
        PerfectOrder(Series.MegaEvolution),
        ChaosRising(Series.MegaEvolution);
    }
}

val seriesToExpansionMap: Map<Series, List<Series.Expansion>> =
    Series.Expansion.entries.groupBy { it.series }
