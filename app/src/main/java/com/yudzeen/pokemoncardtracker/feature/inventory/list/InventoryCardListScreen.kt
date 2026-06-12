package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yudzeen.pokemoncardtracker.core.ui.PokemonCardList

@Composable
fun InventoryCardListScreen(viewModel: InventoryCardListViewModel, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PokemonCardList(uiState.cardList, onItemClick)
}