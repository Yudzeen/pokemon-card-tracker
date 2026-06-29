package com.yudzeen.pokemoncardtracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.yudzeen.pokemoncardtracker.feature.inventory.add.InventoryAddCardScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.add.InventoryAddCardViewModel
import com.yudzeen.pokemoncardtracker.feature.inventory.detail.InventoryCardDetailScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.detail.InventoryCardDetailViewModel
import com.yudzeen.pokemoncardtracker.feature.inventory.list.InventoryCardListScreen
import com.yudzeen.pokemoncardtracker.feature.inventory.list.InventoryCardListViewModel
import com.yudzeen.pokemoncardtracker.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var appBarTitle by remember { mutableStateOf("Pokemon Card Tracker") }
    val backStack = rememberNavBackStack(Route.InventoryCardListRoute)
    val canNavigateBack = backStack.size > 1
    var onFabClick by remember { mutableStateOf({}) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(appBarTitle)
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
        floatingActionButton = {
            val currentRoute = backStack.last()
            when (currentRoute) {
                is Route.InventoryCardListRoute -> {
                    FloatingActionButton(
                        onClick = { backStack.add(Route.InventoryCardAddRoute) }
                    ) {
                        Icon(painterResource(R.drawable.ic_add), "Add card button.")
                    }
                }
                is Route.InventoryCardAddRoute -> {
                    FloatingActionButton(
                        onClick = { onFabClick() }
                    ) {
                        Icon(painterResource(R.drawable.ic_save), "Save card button.")
                    }
                }
                else -> {}
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Route.InventoryCardListRoute> {
                    val viewModel: InventoryCardListViewModel = hiltViewModel()
                    appBarTitle = "Pokemon Card Tracker"
                    InventoryCardListScreen(
                        viewModel = viewModel,
                        onItemClick = { cardId ->
                            backStack.add(Route.InventoryCardDetailRoute(cardId))
                        }
                    )
                }
                entry<Route.InventoryCardDetailRoute> { key ->
                    val viewModel = hiltViewModel<InventoryCardDetailViewModel, InventoryCardDetailViewModel.Factory>(
                        creationCallback = { factory -> factory.create(key) }
                    )
                    appBarTitle = "Card Detail"
                    InventoryCardDetailScreen(
                        viewModel = viewModel,
                        onTitleChanged = { appBarTitle = it }
                    )
                }
                entry<Route.InventoryCardAddRoute> { key ->
                    val viewModel: InventoryAddCardViewModel = hiltViewModel()
                    appBarTitle = "Add Card"
                    onFabClick = { viewModel.save() }
                    InventoryAddCardScreen(
                        viewModel = viewModel,
                        navigateToCardDetail = { cardId ->
                            backStack.add(Route.InventoryCardDetailRoute(cardId))
                            backStack.remove(key)
                        }
                    )
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}