package com.yudzeen.pokemoncardtracker.feature.inventory.add

data class InventoryAddCardViewState(
    val cardName: String = "",
    val ownedQuantity: Int = 0,
    val targetQuantity: Int = 0,
    val selectedSeries: String = "-",
    val seriesOptions: List<String> = emptyList(),
    val selectedExpansion: String = "-",
    val expansionOptions: List<String> = emptyList()
)

sealed interface InventoryAddCardIntent {
    data class UpdateCardName(val newCardName: String): InventoryAddCardIntent
    data class UpdateOwnedQuantity(val newOwnedQuantity: Int): InventoryAddCardIntent
    data class UpdateTargetQuantity(val newTargetQuantity: Int): InventoryAddCardIntent
    data class UpdateSelectedSeries(val newSelectedSeries: String): InventoryAddCardIntent
    data class UpdateSelectedExpansion(val newSelectedExpansion: String): InventoryAddCardIntent
    data object UploadPhoto: InventoryAddCardIntent
}