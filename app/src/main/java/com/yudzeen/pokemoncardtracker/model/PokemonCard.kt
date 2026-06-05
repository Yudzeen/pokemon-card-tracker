package com.yudzeen.pokemoncardtracker.model

import java.util.UUID

data class PokemonCard(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val series: Series,
    val expansion: Series.Expansion
)

sealed interface Series {
    data object Original: Series
    data object Neo: Series
    data object LegendaryCollection: Series
    data object ECard: Series
    data object EX: Series
    data object DiamondPearl: Series
    data object Platinum: Series
    data object HeartGoldSoulSilver: Series
    data object BlackWhite: Series
    data object XY: Series
    data object SunMoon: Series
    data object SwordShield: Series
    data object ScarletViolet: Series
    data object MegaEvolution: Series

    sealed class Expansion(val series: Series) {

        data object Base: Expansion(Original)
        data object Jungle: Expansion(Original)
        data object Fossil: Expansion(Original)
        data object Base2: Expansion(Original)
        data object TeamRocket: Expansion(Original)
        data object GymHeroes: Expansion(Original)
        data object GymChallenge: Expansion(Original)

        data object NeoGenesis: Expansion(Neo)
        data object NeoDiscovery: Expansion(Neo)
        data object NeoRevelation: Expansion(Neo)
        data object NeoDestiny: Expansion(Neo)

        data object LegendaryCollection: Expansion(Series.LegendaryCollection)

        data object Expedition: Expansion(ECard)
        data object Aquapolis: Expansion(ECard)
        data object Skyridge: Expansion(ECard)

        data object RubySapphire: Expansion(EX)
        data object Sandstorm: Expansion(EX)
        data object Dragon: Expansion(EX)
        data object TeamMagmaVsTeamAqua: Expansion(EX)
        data object HiddenLegends: Expansion(EX)
        data object FireRedLeafGreen: Expansion(EX)
        data object TeamRocketReturns: Expansion(EX)
        data object Deoxys: Expansion(EX)
        data object Emerald: Expansion(EX)
        data object UnseenForces: Expansion(EX)
        data object DeltaSpecies: Expansion(EX)
        data object LegendMaker: Expansion(EX)
        data object HolonPhantoms: Expansion(EX)
        data object CrystalGuardians: Expansion(EX)
        data object DragonFrontiers: Expansion(EX)
        data object PowerKeepers: Expansion(EX)

        data object DiamondPearl: Expansion(Series.DiamondPearl)
        data object MysteriousTreasures: Expansion(Series.DiamondPearl)
        data object SecretWonders: Expansion(Series.DiamondPearl)
        data object GreatEncounters: Expansion(Series.DiamondPearl)
        data object MajesticDawn: Expansion(Series.DiamondPearl)
        data object LegendsAwakened: Expansion(Series.DiamondPearl)
        data object Stormfront: Expansion(Series.DiamondPearl)

        data object Platinum: Expansion(Series.Platinum)
        data object RisingRivals: Expansion(Series.Platinum)
        data object SupremeVictors: Expansion(Series.Platinum)
        data object Arceus: Expansion(Series.Platinum)

        data object HeartGoldSoulSilver: Expansion(Series.HeartGoldSoulSilver)
        data object Unleashed: Expansion(Series.HeartGoldSoulSilver)
        data object Undaunted: Expansion(Series.HeartGoldSoulSilver)
        data object Triumphant: Expansion(Series.HeartGoldSoulSilver)
        data object CallOfLegends: Expansion(Series.HeartGoldSoulSilver)

        data object BlackWhite: Expansion(Series.BlackWhite)
        data object EmergingPowers: Expansion(Series.BlackWhite)
        data object NobleVictories: Expansion(Series.BlackWhite)
        data object NextDestinies: Expansion(Series.BlackWhite)
        data object DarkExplorers: Expansion(Series.BlackWhite)
        data object DragonsExalted: Expansion(Series.BlackWhite)
        data object DragonVault: Expansion(Series.BlackWhite)
        data object BoundariesCrossed: Expansion(Series.BlackWhite)
        data object PlasmaStorm: Expansion(Series.BlackWhite)
        data object PlasmaFreeze: Expansion(Series.BlackWhite)
        data object PlasmaBlast: Expansion(Series.BlackWhite)
        data object LegendaryTreasures: Expansion(Series.BlackWhite)

        data object KalosStarterSet: Expansion(Series.XY)
        data object XY: Expansion(Series.XY)
        data object Flashfire: Expansion(Series.XY)
        data object FuriousFists: Expansion(Series.XY)
        data object PhantomForces: Expansion(Series.XY)
        data object PrimalClash: Expansion(Series.XY)
        data object DoubleCrisis: Expansion(Series.XY)
        data object RoaringSkies: Expansion(Series.XY)
        data object AncientOrigins: Expansion(Series.XY)
        data object Breakthrough: Expansion(Series.XY)
        data object Breakpoint: Expansion(Series.XY)
        data object Generations: Expansion(Series.XY)
        data object FatesCollide: Expansion(Series.XY)
        data object SteamSiege: Expansion(Series.XY)
        data object Evolutions: Expansion(Series.XY)

        data object SunMoon: Expansion(Series.SunMoon)
        data object GuardiansRising: Expansion(Series.SunMoon)
        data object BurningShadows: Expansion(Series.SunMoon)
        data object ShiningLegends: Expansion(Series.SunMoon)
        data object CrimsonInvasion: Expansion(Series.SunMoon)
        data object UltraPrism: Expansion(Series.SunMoon)
        data object ForbiddenLight: Expansion(Series.SunMoon)
        data object CelestialStorm: Expansion(Series.SunMoon)
        data object DragonMajesty: Expansion(Series.SunMoon)
        data object LostThunder: Expansion(Series.SunMoon)
        data object TeamUp: Expansion(Series.SunMoon)
        data object DetectivePikachu: Expansion(Series.SunMoon)
        data object UnbrokenBonds: Expansion(Series.SunMoon)
        data object UnifiedMinds: Expansion(Series.SunMoon)
        data object HiddenFates: Expansion(Series.SunMoon)
        data object CosmicEclipse: Expansion(Series.SunMoon)

        data object SwordShield: Expansion(Series.SwordShield)
        data object RebelClash: Expansion(Series.SwordShield)
        data object DarknessAblaze: Expansion(Series.SwordShield)
        data object ChampionsPath: Expansion(Series.SwordShield)
        data object VividVoltage: Expansion(Series.SwordShield)
        data object ShiningFates: Expansion(Series.SwordShield)
        data object ChillingReign: Expansion(Series.SwordShield)
        data object EvolvingSkies: Expansion(Series.SwordShield)
        data object Celebrations: Expansion(Series.SwordShield)
        data object FusionStrike: Expansion(Series.SwordShield)
        data object BrilliantStars: Expansion(Series.SwordShield)
        data object AstralRadiance: Expansion(Series.SwordShield)
        data object PokemonGo: Expansion(Series.SwordShield)
        data object LostOrigin: Expansion(Series.SwordShield)
        data object SilverTempest: Expansion(Series.SwordShield)
        data object CrownZenith: Expansion(Series.SwordShield)

        data object ScarletViolet: Expansion(Series.ScarletViolet)
        data object PaldeaEvolved: Expansion(Series.ScarletViolet)
        data object ObsidianFlames: Expansion(Series.ScarletViolet)
        data object SV151: Expansion(Series.ScarletViolet)
        data object ParadoxRift: Expansion(Series.ScarletViolet)
        data object PaldeanFates: Expansion(Series.ScarletViolet)
        data object TemporalForces: Expansion(Series.ScarletViolet)
        data object TwilightMasquerade: Expansion(Series.ScarletViolet)
        data object ShroudedFable: Expansion(Series.ScarletViolet)
        data object StellarCrown: Expansion(Series.ScarletViolet)
        data object SurgingSparks: Expansion(Series.ScarletViolet)
        data object PrismaticEvolutions: Expansion(Series.ScarletViolet)
        data object JourneyTogether: Expansion(Series.ScarletViolet)
        data object DestinedRivals: Expansion(Series.ScarletViolet)
        data object BlackBolt: Expansion(Series.ScarletViolet)
        data object WhiteFlare: Expansion(Series.ScarletViolet)

        data object MegaEvolution: Expansion(Series.MegaEvolution)
        data object PhantasmalFlames: Expansion(Series.MegaEvolution)
        data object AscendedHeroes: Expansion(Series.MegaEvolution)
        data object PerfectOrder: Expansion(Series.MegaEvolution)
        data object ChaosRising: Expansion(Series.MegaEvolution)

    }
}

