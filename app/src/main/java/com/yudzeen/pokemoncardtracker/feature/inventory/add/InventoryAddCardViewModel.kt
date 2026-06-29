package com.yudzeen.pokemoncardtracker.feature.inventory.add

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudzeen.pokemoncardtracker.core.model.Category
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.Series
import com.yudzeen.pokemoncardtracker.core.model.Variant
import com.yudzeen.pokemoncardtracker.core.model.seriesToExpansionMap
import com.yudzeen.pokemoncardtracker.core.repository.PokemonCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryAddCardViewModel @Inject constructor(
    private val pokemonCardRepository: PokemonCardRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(InventoryAddCardViewState())
    val uiState: StateFlow<InventoryAddCardViewState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<InventoryAddCardUiEvent>()   // TODO: Anti-pattern
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val seriesOptions = seriesToExpansionMap.keys.map { it.toString() }
        val expansionOptions = seriesToExpansionMap.values.flatten().map { it.toString() }
        val categoryOptions = Category.entries.map { it.toString() }
        val variantOptions = Variant.entries.map { it.toString() }
        _uiState.update {
            it.copy(
                seriesOptions = seriesOptions,
                expansionOptions = expansionOptions,
                categoryOptions = categoryOptions,
                variantOptions = variantOptions
            )
        }
    }

    fun handleIntent(intent: InventoryAddCardIntent) {
        with(intent) {
            when (this) {
                is InventoryAddCardIntent.UpdateCardName -> updateCardName(newCardName)
                is InventoryAddCardIntent.UpdateOwnedQuantity -> updateOwnedQuantity(newOwnedQuantity)
                is InventoryAddCardIntent.UpdateTargetQuantity -> updateTargetQuantity(newTargetQuantity)
                is InventoryAddCardIntent.UpdateSelectedExpansion -> updateSelectedExpansion(newSelectedExpansion)
                is InventoryAddCardIntent.UpdateSelectedSeries -> updateSelectedSeries(newSelectedSeries)
                is InventoryAddCardIntent.UpdatePhotoUri -> updatePhotoUri(newPhotoUri)
                is InventoryAddCardIntent.UpdateSelectedCategory -> updateSelectedCategory(newSelectedCategory)
                is InventoryAddCardIntent.UpdateSelectedVariant -> updateSelectedVariant(newSelectedVariant)
            }
        }
    }

    private fun updateCardName(newValue: String) {
        _uiState.update {
            it.copy(
                cardName = newValue,
                errors = it.errors.toMutableSet().apply { remove(InventoryAddCardError.NoCardNameIndicated) }
            )
        }
    }

    private fun updateOwnedQuantity(newValue: Int) {
        _uiState.update { it.copy(ownedQuantity = newValue) }
    }

    private fun updateTargetQuantity(newValue: Int) {
        _uiState.update { it.copy(targetQuantity = newValue) }
    }

    private fun updateSelectedSeries(newValue: String) {
        val series = Series.valueOf(newValue)
        val expansionOptions: List<String> = seriesToExpansionMap[series]?.map { it.toString() } ?: emptyList()
        _uiState.update {
            it.copy(
                selectedSeries = newValue,
                expansionOptions = expansionOptions,
                errors = it.errors.toMutableSet().apply { remove(InventoryAddCardError.NoSeriesSelected) }
            )
        }
    }

    private fun updateSelectedExpansion(newValue: String) {
        val expansion = Series.Expansion.valueOf(newValue)
        val selectedSeries = expansion.series.toString()
        _uiState.update {
            it.copy(
                selectedSeries = selectedSeries,
                selectedExpansion = newValue,
                errors = it.errors.toMutableSet().apply { remove(InventoryAddCardError.NoExpansionSelected) }
            )
        }
    }

    private fun updatePhotoUri(newValue: String) {
        _uiState.update {
            it.copy(
                photoUri = newValue,
                errors = it.errors.toMutableSet().apply { remove(InventoryAddCardError.NoPhotoSelected) }
            )
        }
    }

    private fun updateSelectedCategory(newValue: String) {
        _uiState.update {
            it.copy(
                selectedCategory = newValue,
                errors = it.errors.toMutableSet().apply { remove(InventoryAddCardError.NoCategorySelected) }
            )
        }
    }

    private fun updateSelectedVariant(newValue: String) {
        _uiState.update {
            it.copy(
                selectedVariant = newValue,
                errors = it.errors.toMutableSet().apply { remove(InventoryAddCardError.NoVariantSelected) }
            )
        }
    }

    fun save() {
        viewModelScope.launch {
            with(uiState.value) {
                if (cardName.isNotBlank() &&
                    selectedSeries != null &&
                    selectedExpansion != null &&
                    selectedCategory != null &&
                    selectedVariant != null &&
                    photoUri != null) {
                    try {
                        val pokemonCard = PokemonCard(
                            name = cardName,
                            series = Series.valueOf(selectedSeries),
                            expansion = Series.Expansion.valueOf(selectedExpansion),
                            category = Category.valueOf(selectedCategory),
                            variant = Variant.valueOf(selectedVariant),
                            ownedQuantity = ownedQuantity,
                            targetQuantity = targetQuantity,
                            favorite = favorite,
                            imageUri = photoUri.toUri()
                        )
                        pokemonCardRepository.insert(pokemonCard)
                        _uiEvent.send(InventoryAddCardUiEvent.AddCardSuccess(pokemonCard.id))
                    } catch (e: Exception) {

                    }
                } else {
                    val errors = mutableSetOf<InventoryAddCardError>()
                    if (cardName.isBlank()) { errors.add(InventoryAddCardError.NoCardNameIndicated) }
                    if (selectedSeries == null) { errors.add(InventoryAddCardError.NoSeriesSelected) }
                    if (selectedExpansion == null) { errors.add(InventoryAddCardError.NoExpansionSelected) }
                    if (selectedCategory == null) { errors.add(InventoryAddCardError.NoCategorySelected) }
                    if (selectedVariant == null) { errors.add(InventoryAddCardError.NoVariantSelected) }
                    if (photoUri == null) { errors.add(InventoryAddCardError.NoPhotoSelected) }
                    _uiState.update { it.copy(errors = errors) }
                }
            }
        }
    }
}