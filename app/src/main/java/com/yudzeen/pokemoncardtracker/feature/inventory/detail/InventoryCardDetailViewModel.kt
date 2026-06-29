package com.yudzeen.pokemoncardtracker.feature.inventory.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudzeen.pokemoncardtracker.core.model.sampleList
import com.yudzeen.pokemoncardtracker.core.repository.PokemonCardRepository
import com.yudzeen.pokemoncardtracker.navigation.Route
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel(assistedFactory = InventoryCardDetailViewModel.Factory::class)
class InventoryCardDetailViewModel @AssistedInject constructor(
    @Assisted val navKey: Route.InventoryCardDetailRoute,
    private val pokemonCardRepository: PokemonCardRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(InventoryCardDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        Log.d("InventoryCardDetailViewModel", "NavKey: ${navKey.cardId}")
        viewModelScope.launch {
            val pokemonCard = pokemonCardRepository.getById(UUID.fromString(navKey.cardId))
            _uiState.update { it.copy(pokemonCard = pokemonCard) }
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(navKey: Route.InventoryCardDetailRoute): InventoryCardDetailViewModel
    }
}