package com.yudzeen.pokemoncardtracker.feature.inventory.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yudzeen.pokemoncardtracker.core.model.sampleList
import com.yudzeen.pokemoncardtracker.navigation.Route
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel(assistedFactory = InventoryCardDetailViewModel.Factory::class)
class InventoryCardDetailViewModel @AssistedInject constructor(
    @Assisted val navKey: Route.InventoryCardDetailRoute
): ViewModel() {

    private val _uiState = MutableStateFlow(InventoryCardDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        Log.d("InventoryCardDetailViewModel", "NavKey: ${navKey.cardId}")
        val pokemonCard = sampleList.firstOrNull { it.id.toString() == navKey.cardId }
        _uiState.value = InventoryCardDetailUiState(pokemonCard)
    }


    @AssistedFactory
    interface Factory {
        fun create(navKey: Route.InventoryCardDetailRoute): InventoryCardDetailViewModel
    }
}