package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.TopAppBarState
import com.yudzeen.pokemoncardtracker.core.model.seriesToCardListMap
import com.yudzeen.pokemoncardtracker.feature.inventory.list.views.PokemonCardList
import com.yudzeen.pokemoncardtracker.ui.views.conditional

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryCardListScreen(
    viewModel: InventoryCardListViewModel,
    onItemClick: (String) -> Unit,
    updateTopAppBar: (TopAppBarState) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    updateTopAppBar(
        TopAppBarState(
            title = "Pokemon Card Tracker",
            actions = {
                TopAppBarCardListActions(uiState.favoriteFilter) {
                    viewModel.handleIntent(InventoryCardListIntent.FilterByFavorite)
                }
            },
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        )
    )
    InventoryCardListScreen(uiState, onItemClick, modifier)
}

@Composable
internal fun InventoryCardListScreen(
    uiState: InventoryCardListUiState,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        PokemonCardList(uiState.seriesToCardListMap, onItemClick)
    }
}

@Composable
fun TopAppBarCardListActions(favoriteFilter: Boolean, onFilterByFavorite: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            painter = painterResource(R.drawable.ic_more_vertical),
            contentDescription = "More options"
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text("Filter by Favorite") },
            onClick = {
                showMenu = false
                onFilterByFavorite()
            },
            modifier = Modifier
                .conditional(favoriteFilter) {
                    background(Color.Gray)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun InventoryCardListScreenPreview() {
    InventoryCardListScreen(
        uiState = InventoryCardListUiState(seriesToCardListMap),
        onItemClick = { }
    )
}