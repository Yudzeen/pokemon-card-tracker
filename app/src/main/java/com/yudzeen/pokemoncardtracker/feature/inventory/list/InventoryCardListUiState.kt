package com.yudzeen.pokemoncardtracker.feature.inventory.list

import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.Series

data class InventoryCardListUiState(
    val seriesToCardListMap: Map<Series, List<PokemonCard>> = emptyMap(),
    val favoriteFilter: Boolean = false
)

sealed interface InventoryCardListIntent {
    data object FilterByFavorite: InventoryCardListIntent
}