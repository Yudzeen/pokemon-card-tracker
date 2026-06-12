package com.yudzeen.pokemoncardtracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.yudzeen.pokemoncardtracker.feature.inventory.detail.InventoryCardDetailScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.list.InventoryCardListScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.list.InventoryCardListViewModel
import com.yudzeen.pokemoncardtracker.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(Route.InventoryCardListRoute)
    val canNavigateBack = backStack.size > 1

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokemon Card Tracker")
                },
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(
                            onClick = {
                                backStack.removeLastOrNull()
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                                contentDescription = "Navigate back"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary                            )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
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
            modifier = Modifier.padding(innerPadding)
        )
    }
}