package com.yudzeen.pokemoncardtracker.feature.inventory.add

import java.util.UUID

sealed interface InventoryAddCardUiEvent {
    data class AddCardSuccess(val cardId: UUID): InventoryAddCardUiEvent
    data object AddCardError: InventoryAddCardUiEvent
}