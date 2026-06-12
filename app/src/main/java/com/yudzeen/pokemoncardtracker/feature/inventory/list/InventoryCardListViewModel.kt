package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.lifecycle.ViewModel
import com.yudzeen.pokemoncardtracker.core.ui.PokemonCardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

@HiltViewModel
class InventoryCardListViewModel @Inject constructor(): ViewModel() {

    val sampleList = List(10) {
        PokemonCardUiState(UUID.randomUUID().toString(), "Card #$it")
    }

    private val _uiState = MutableStateFlow<InventoryCardListUiState>(InventoryCardListUiState(sampleList))
    val uiState = _uiState.asStateFlow()

}