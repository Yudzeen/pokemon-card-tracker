package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.Series
import com.yudzeen.pokemoncardtracker.core.model.sampleList
import com.yudzeen.pokemoncardtracker.core.model.seriesToCardListMap
import com.yudzeen.pokemoncardtracker.core.repository.PokemonCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.set

@HiltViewModel
class InventoryCardListViewModel @Inject constructor(
    private val pokemonCardRepository: PokemonCardRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(InventoryCardListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            pokemonCardRepository.getAll().collect { cardList ->
                val seriesSet = cardList.map { it.series }.toSet()
                val seriesToCardListMap = mutableMapOf<Series, List<PokemonCard>>().apply {
                    seriesSet.forEach { series ->
                        this[series] = cardList.filter { it.series == series }
                    }
                }
                _uiState.value = InventoryCardListUiState(
                    seriesToCardListMap = seriesToCardListMap
                )
            }
        }
    }

}