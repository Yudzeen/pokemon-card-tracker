package com.yudzeen.pokemoncardtracker.feature.inventory.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.Series
import com.yudzeen.pokemoncardtracker.core.repository.PokemonCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InventoryCardListViewModel @Inject constructor(
    private val pokemonCardRepository: PokemonCardRepository
): ViewModel() {

    private val favoriteFilter = MutableStateFlow(false)

    val uiState: StateFlow<InventoryCardListUiState> =
        combine(pokemonCardRepository.getAll(), favoriteFilter) { list, favoriteFilter ->
            val filteredList = if (favoriteFilter) {
                list.filter { it.favorite  }
            } else {
                list
            }
            InventoryCardListUiState(
                seriesToCardListMap = filteredList.mapToSeries(),
                favoriteFilter = favoriteFilter
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InventoryCardListUiState()
        )

    fun handleIntent(intent: InventoryCardListIntent) {
        when (intent) {
            InventoryCardListIntent.FilterByFavorite -> filterByFavorite()
        }
    }

    private fun filterByFavorite() {
        favoriteFilter.update { value -> !value }
    }

    private fun List<PokemonCard>.mapToSeries(): Map<Series, List<PokemonCard>> {
        val cardList = this
        val seriesSet = cardList.map { it.series }.toSet()
        val seriesToCardListMap = mutableMapOf<Series, List<PokemonCard>>().apply {
            seriesSet.forEach { series ->
                this[series] = cardList.filter { it.series == series }
            }
        }
        return seriesToCardListMap
    }

}