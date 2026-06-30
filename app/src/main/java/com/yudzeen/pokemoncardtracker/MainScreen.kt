package com.yudzeen.pokemoncardtracker

import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.yudzeen.pokemoncardtracker.ui.views.ifNotNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(Route.InventoryCardListRoute)
    val canNavigateBack = backStack.size > 1
    var onFabClick by remember { mutableStateOf({}) }
    var topAppBarState by remember { mutableStateOf(TopAppBarState()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(topAppBarState.title)
                },
                actions = topAppBarState.actions,
                scrollBehavior = topAppBarState.scrollBehavior,
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
                    titleContentColor = MaterialTheme.colorScheme.primary)
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
        modifier = Modifier
            .fillMaxSize()
            .ifNotNull(topAppBarState.scrollBehavior) { scrollBehavior ->
                nestedScroll(scrollBehavior.nestedScrollConnection)
            }
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
                    InventoryCardListScreen(
                        viewModel = viewModel,
                        updateTopAppBar = { topAppBarState = it },
                        onItemClick = { cardId ->
                            backStack.add(Route.InventoryCardDetailRoute(cardId))
                        }
                    )
                }
                entry<Route.InventoryCardDetailRoute> { key ->
                    val viewModel = hiltViewModel<InventoryCardDetailViewModel, InventoryCardDetailViewModel.Factory>(
                        creationCallback = { factory -> factory.create(key) }
                    )
                    InventoryCardDetailScreen(
                        viewModel = viewModel,
                        updateTopAppBar = { topAppBarState = it },
                        onCardDeleted = { backStack.removeLastOrNull() }
                    )
                }
                entry<Route.InventoryCardAddRoute> { key ->
                    val viewModel: InventoryAddCardViewModel = hiltViewModel()
                    topAppBarState = TopAppBarState("Add Card")
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

@OptIn(ExperimentalMaterial3Api::class)
data class TopAppBarState(
    val title: String = "",
    val actions: @Composable RowScope.() -> Unit = {},
    val scrollBehavior: TopAppBarScrollBehavior? = null
)
