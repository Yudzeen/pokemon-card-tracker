package com.yudzeen.pokemoncardtracker.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.sampleList

@Composable
fun PokemonCardListItem(pokemonCard: PokemonCard, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = { onItemClick(pokemonCard.name) }
    ) {
        AsyncImage(
            model = pokemonCard.imgLowRes,
            contentDescription = pokemonCard.name,
            placeholder = painterResource(R.drawable.pokemon_card_back_side),
            modifier = Modifier.size(100.dp)
        )
        Text(pokemonCard.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardListItemPreview() {
    PokemonCardListItem(
        pokemonCard = sampleList.first(),
        onItemClick = { },
    )
}
