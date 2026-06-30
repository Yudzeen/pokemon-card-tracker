package com.yudzeen.pokemoncardtracker.feature.inventory.detail

import com.yudzeen.pokemoncardtracker.core.model.PokemonCard

data class InventoryCardDetailUiState(
    val pokemonCard: PokemonCard? = null
)

sealed interface InventoryCardDetailIntent {
    data class UpdateOwnedQuantity(val newValue: Int): InventoryCardDetailIntent
    data class UpdateTargetQuantity(val newValue: Int): InventoryCardDetailIntent
    data object ToggleFavorite: InventoryCardDetailIntent
    data object DeleteCard: InventoryCardDetailIntent
}