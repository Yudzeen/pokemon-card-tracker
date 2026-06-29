package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yudzeen.pokemoncardtracker.core.model.seriesToCardListMap
import com.yudzeen.pokemoncardtracker.feature.inventory.list.views.PokemonCardList

@Composable
fun InventoryCardListScreen(
    viewModel: InventoryCardListViewModel,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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

@Preview(showBackground = true)
@Composable
internal fun InventoryCardListScreenPreview() {
    InventoryCardListScreen(
        uiState = InventoryCardListUiState(seriesToCardListMap),
        onItemClick = { }
    )
}