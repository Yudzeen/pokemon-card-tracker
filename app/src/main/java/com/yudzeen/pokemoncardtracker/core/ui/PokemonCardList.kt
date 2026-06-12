package com.yudzeen.pokemoncardtracker.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.Series
import com.yudzeen.pokemoncardtracker.core.model.seriesToCardListMap

@Composable
fun PokemonCardList(seriesToCardListMap: Map<Series, List<PokemonCard>>, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        seriesToCardListMap.forEach { (series, cardList) ->
            stickyHeader { SeriesHeader(series.toString()) }
            items(1) {
                LazyRow {
                    items(cardList) { card ->
                        PokemonCardListItem(card, onItemClick)
                    }
                }
            }
        }
    }
}

@Composable
fun SeriesHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PokemonCardListPreview() {
    PokemonCardList(seriesToCardListMap, {})
}