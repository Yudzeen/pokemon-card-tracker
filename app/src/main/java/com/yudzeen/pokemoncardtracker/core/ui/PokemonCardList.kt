package com.yudzeen.pokemoncardtracker.core.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.util.UUID

@Composable
fun PokemonCardList(list: List<PokemonCardUiState>, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn() {
        items(list) {
            PokemonCardListItem(it, onItemClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardListPreview() {
    PokemonCardList(sampleList, {})
}

val sampleList = List(10) {
    PokemonCardUiState(UUID.randomUUID().toString(), "Card #$it")
}