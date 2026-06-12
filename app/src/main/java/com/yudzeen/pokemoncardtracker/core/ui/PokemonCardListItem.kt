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
import java.util.UUID

data class PokemonCardUiState(
    val id: String,
    val text: String
)

@Composable
fun PokemonCardListItem(uiState: PokemonCardUiState, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = { onItemClick(uiState.text) }
    ) {
        AsyncImage(
            model = "https://assets.tcgdex.net/en/swsh/swsh3/136/low.webp",
            contentDescription = uiState.text,
            placeholder = painterResource(R.drawable.pokemon_card_back_side),
            modifier = Modifier.size(100.dp)
        )
        Text(uiState.text)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardListItemPreview() {
    PokemonCardListItem(
        uiState = PokemonCardUiState(
            id = UUID.randomUUID().toString(),
            text = "Pikachu"
        ),
        onItemClick = { },
    )
}
