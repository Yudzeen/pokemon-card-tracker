package com.yudzeen.pokemoncardtracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.yudzeen.pokemoncardtracker.feature.inventory.detail.InventoryCardDetailScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.list.InventoryCardListScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.list.InventoryCardListViewModel

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Route.InventoryCardListRoute)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.InventoryCardListRoute> {
                val viewModel: InventoryCardListViewModel = hiltViewModel()
                InventoryCardListScreen(
                    viewModel = viewModel,
                    onItemClick = {
                        backStack.add(Route.InventoryCardDetailRoute(it))
                    }
                )
            }
            entry<Route.InventoryCardDetailRoute> {
                InventoryCardDetailScreen(it.cardId)
            }
        },
        modifier = modifier
    )
}