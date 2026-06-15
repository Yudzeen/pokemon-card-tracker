package com.yudzeen.pokemoncardtracker.feature.inventory.list.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
            item {
                FlowRow(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    cardList.forEach { card ->
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