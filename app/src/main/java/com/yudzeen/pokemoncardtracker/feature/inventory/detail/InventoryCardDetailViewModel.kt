package com.yudzeen.pokemoncardtracker.feature.inventory.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudzeen.pokemoncardtracker.core.repository.PokemonCardRepository
import com.yudzeen.pokemoncardtracker.navigation.Route
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel(assistedFactory = InventoryCardDetailViewModel.Factory::class)
class InventoryCardDetailViewModel @AssistedInject constructor(
    @Assisted val navKey: Route.InventoryCardDetailRoute,
    private val pokemonCardRepository: PokemonCardRepository
): ViewModel() {

    val uiState: StateFlow<InventoryCardDetailUiState> = pokemonCardRepository.getById(UUID.fromString(navKey.cardId))
        .map { InventoryCardDetailUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InventoryCardDetailUiState()
        )

    fun handleIntent(intent: InventoryCardDetailIntent) {
        when (intent) {
            is InventoryCardDetailIntent.UpdateOwnedQuantity -> updateOwnedQuantity(intent.newValue)
            is InventoryCardDetailIntent.UpdateTargetQuantity -> updateTargetQuantity(intent.newValue)
            InventoryCardDetailIntent.ToggleFavorite -> toggleFavorite()
        }
    }

    private fun updateOwnedQuantity(newValue: Int) {
        viewModelScope.launch {
            pokemonCardRepository.updateOwnedQuantity(UUID.fromString(navKey.cardId), newValue)
        }
    }

    private fun updateTargetQuantity(newValue: Int) {
        viewModelScope.launch {
            pokemonCardRepository.updateTargetQuantity(UUID.fromString(navKey.cardId), newValue)
        }
    }

    private fun toggleFavorite() {
        viewModelScope.launch {
            pokemonCardRepository.toggleFavorite(UUID.fromString(navKey.cardId))
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(navKey: Route.InventoryCardDetailRoute): InventoryCardDetailViewModel
    }
}