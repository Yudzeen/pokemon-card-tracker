package com.yudzeen.pokemoncardtracker.feature.inventory.add

data class InventoryAddCardViewState(
    val cardName: String = "",
    val ownedQuantity: Int = 0,
    val targetQuantity: Int = 0,
    val selectedSeries: String? = null,
    val seriesOptions: List<String> = emptyList(),
    val selectedExpansion: String? = null,
    val expansionOptions: List<String> = emptyList(),
    val photoUri: String? = null,
    val favorite: Boolean = false,
    val selectedCategory: String? = null,
    val categoryOptions: List<String> = emptyList(),
    val selectedVariant: String? = null,
    val variantOptions: List<String> = emptyList(),
    val errors: Set<InventoryAddCardError> = emptySet()
)

sealed interface InventoryAddCardIntent {
    data class UpdateCardName(val newCardName: String): InventoryAddCardIntent
    data class UpdateOwnedQuantity(val newOwnedQuantity: Int): InventoryAddCardIntent
    data class UpdateTargetQuantity(val newTargetQuantity: Int): InventoryAddCardIntent
    data class UpdateSelectedSeries(val newSelectedSeries: String): InventoryAddCardIntent
    data class UpdateSelectedExpansion(val newSelectedExpansion: String): InventoryAddCardIntent
    data class UpdateSelectedCategory(val newSelectedCategory: String): InventoryAddCardIntent
    data class UpdateSelectedVariant(val newSelectedVariant: String): InventoryAddCardIntent
    data class UpdatePhotoUri(val newPhotoUri: String): InventoryAddCardIntent
}