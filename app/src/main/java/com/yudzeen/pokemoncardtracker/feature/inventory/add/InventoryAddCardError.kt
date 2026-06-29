package com.yudzeen.pokemoncardtracker.feature.inventory.add

sealed interface InventoryAddCardError {
    object NoCardNameIndicated: InventoryAddCardError
    object NoSeriesSelected: InventoryAddCardError
    object NoExpansionSelected: InventoryAddCardError
    object NoCategorySelected: InventoryAddCardError
    object NoVariantSelected: InventoryAddCardError
    object NoPhotoSelected: InventoryAddCardError
}
