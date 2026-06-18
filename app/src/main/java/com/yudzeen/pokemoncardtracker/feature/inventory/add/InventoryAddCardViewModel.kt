package com.yudzeen.pokemoncardtracker.feature.inventory.add

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.yudzeen.pokemoncardtracker.core.model.Series
import com.yudzeen.pokemoncardtracker.core.model.seriesToExpansionMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InventoryAddCardViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(InventoryAddCardViewState())
    val uiState: StateFlow<InventoryAddCardViewState> = _uiState.asStateFlow()

    init {
        val seriesOptions = seriesToExpansionMap.keys.map { it.toString() }
        val expansionOptions = seriesToExpansionMap.values.flatten().map { it.toString() }
        _uiState.update {
            it.copy(
                seriesOptions = seriesOptions,
                expansionOptions = expansionOptions
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
                is InventoryAddCardIntent.UploadPhoto -> uploadPhoto()
            }
        }
    }

    private fun updateCardName(newValue: String) {
        _uiState.update { it.copy(cardName = newValue) }
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
                expansionOptions = expansionOptions
            )
        }
    }

    private fun updateSelectedExpansion(newValue: String) {
        val expansion = Series.Expansion.valueOf(newValue)
        val selectedSeries = expansion.series.toString()
        _uiState.update {
            it.copy(
                selectedSeries = selectedSeries,
                selectedExpansion = newValue
            )
        }
    }

    private fun uploadPhoto() {
        // TODO: or side effect?
    }

    fun save(context: Context) {
        Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
        // TODO: Save on DB
    }
}