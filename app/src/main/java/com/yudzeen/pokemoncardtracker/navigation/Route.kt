package com.yudzeen.pokemoncardtracker.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object InventoryCardListRoute: Route, NavKey

    @Serializable
    data class InventoryCardDetailRoute(val cardId: String): Route, NavKey

    @Serializable
    data object InventoryCardAddRoute: Route, NavKey
}