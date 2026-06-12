package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.lifecycle.ViewModel
import com.yudzeen.pokemoncardtracker.core.model.seriesToCardListMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InventoryCardListViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(InventoryCardListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = InventoryCardListUiState(
                seriesToCardListMap = seriesToCardListMap
            )
    }

}