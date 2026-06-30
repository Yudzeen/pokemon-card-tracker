package com.yudzeen.pokemoncardtracker.feature.inventory.detail

sealed interface InventoryCardDetailEvent {
    data object CardDeleted: InventoryCardDetailEvent
}