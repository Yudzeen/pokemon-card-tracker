package com.yudzeen.pokemoncardtracker.model

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
    val favorite: Boolean
)

