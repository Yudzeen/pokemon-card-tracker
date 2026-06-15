package com.yudzeen.pokemoncardtracker.core.model

import java.util.UUID

data class PokemonCard(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val series: Series,
    val expansion: Series.Expansion,
    val category: Category,
    val variant: Variant,
    val ownedQuantity: Int,
    val targetQuantity: Int,
    val favorite: Boolean,
    val imgHighRes: String,
    val imgLowRes: String
)

val sampleList = listOf(
    PokemonCard(
        name = "Switch",
        series = Series.ECard,
        expansion = Series.Expansion.Expedition,
        category = Category.TRAINER,
        variant = Variant.REGULAR,
        ownedQuantity = 4,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/ecard/ecard1/157/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/ecard/ecard1/157/low.webp"
    ),
    PokemonCard(
        name = "Copycat",
        series = Series.ECard,
        expansion = Series.Expansion.Expedition,
        category = Category.TRAINER,
        variant = Variant.REGULAR,
        ownedQuantity = 4,
        targetQuantity = 4,
        favorite = false,
        imgHighRes = "https://assets.tcgdex.net/en/ecard/ecard1/138/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/ecard/ecard1/138/low.webp"
    ),
    PokemonCard(
        name = "Rare Candy",
        series = Series.SwordShield,
        expansion = Series.Expansion.PokemonGo,
        category = Category.TRAINER,
        variant = Variant.REGULAR,
        ownedQuantity = 4,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/swsh/swsh10.5/069/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/swsh/swsh10.5/069/low.webp"
    ),
    PokemonCard(
        name = "Boss's Orders",
        series = Series.SwordShield,
        expansion = Series.Expansion.RebelClash,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 4,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/swsh/swsh2/154/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/swsh/swsh2/154/low.webp"
    ),
    PokemonCard(
        name = "Energy Retrieval",
        series = Series.XY,
        expansion = Series.Expansion.Evolutions,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 1,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/xy/xy12/77/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/xy/xy12/77/low.webp"
    ),
    PokemonCard(
        name = "Judge",
        series = Series.XY,
        expansion = Series.Expansion.Breakthrough,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 1,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/xy/xy8/143/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/xy/xy8/143/low.webp"
    ),
    PokemonCard(
        name = "Energy Switch",
        series = Series.EX,
        expansion = Series.Expansion.RubySapphire,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 1,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/ex/ex1/82/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/ex/ex1/82/low.webp"
    ),
    PokemonCard(
        name = "Sacred Ash",
        series = Series.XY,
        expansion = Series.Expansion.Flashfire,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 1,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/xy/xy2/96/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/xy/xy2/96/low.webp"
    ),
    PokemonCard(
        name = "Rare Candy",
        series = Series.XY,
        expansion = Series.Expansion.PrimalClash,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 1,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/xy/xy5/135/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/xy/xy5/135/low.webp"
    ),
    PokemonCard(
        name = "Super Rod",
        series = Series.XY,
        expansion = Series.Expansion.Breakthrough,
        category = Category.TRAINER,
        variant = Variant.HOLO,
        ownedQuantity = 1,
        targetQuantity = 4,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/xy/xy8/149/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/xy/xy8/149/low.webp"
    ),
    PokemonCard(
        name = "Switch",
        series = Series.XY,
        expansion = Series.Expansion.Evolutions,
        category = Category.TRAINER,
        variant = Variant.REVERSE_HOLO,
        ownedQuantity = 2,
        targetQuantity = 2,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/xy/xy12/88/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/xy/xy12/88/low.webp"
    ),
    PokemonCard(
        name = "Counter Catcher",
        series = Series.SunMoon,
        expansion = Series.Expansion.CrimsonInvasion,
        category = Category.TRAINER,
        variant = Variant.REVERSE_HOLO,
        ownedQuantity = 2,
        targetQuantity = 2,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/sm/sm4/91/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/sm/sm4/91/low.webp"
    ),
    PokemonCard(
        name = "Escape Rope",
        series = Series.SunMoon,
        expansion = Series.Expansion.BurningShadows,
        category = Category.TRAINER,
        variant = Variant.REVERSE_HOLO,
        ownedQuantity = 2,
        targetQuantity = 2,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/sm/sm3/114/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/sm/sm3/114/low.webp"
    ),
    PokemonCard(
        name = "Energy Search",
        series = Series.Original,
        expansion = Series.Expansion.Fossil,
        category = Category.TRAINER,
        variant = Variant.REVERSE_HOLO,
        ownedQuantity = 2,
        targetQuantity = 2,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/base/base3/59/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/base/base3/59/low.webp"
    ),
    PokemonCard(
        name = "Energy Search",
        series = Series.DiamondPearl,
        expansion = Series.Expansion.MajesticDawn,
        category = Category.TRAINER,
        variant = Variant.REVERSE_HOLO,
        ownedQuantity = 2,
        targetQuantity = 2,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/dp/dp5/90/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/dp/dp5/90/low.webp"
    ),
    PokemonCard(
        name = "Pokemon Center Lady",
        series = Series.SwordShield,
        expansion = Series.Expansion.ChampionsPath,
        category = Category.TRAINER,
        variant = Variant.REVERSE_HOLO,
        ownedQuantity = 2,
        targetQuantity = 2,
        favorite = true,
        imgHighRes = "https://assets.tcgdex.net/en/swsh/swsh1/176/high.webp",
        imgLowRes = "https://assets.tcgdex.net/en/swsh/swsh1/176/low.webp"
    )
)

val seriesSet = sampleList.map { it.series }.toSet()
val seriesToCardListMap = mutableMapOf<Series, List<PokemonCard>>().apply {
    seriesSet.forEach { series ->
        this[series] = sampleList.filter { it.series == series }
    }
}
