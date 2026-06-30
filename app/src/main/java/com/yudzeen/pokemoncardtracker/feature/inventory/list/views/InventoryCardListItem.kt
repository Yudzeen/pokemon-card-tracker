package com.yudzeen.pokemoncardtracker.feature.inventory.list.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.sampleList

@Composable
fun PokemonCardListItem(pokemonCard: PokemonCard, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(4.dp)
    Card(
        onClick = { onItemClick(pokemonCard.id.toString()) },
        modifier = modifier
            .background(Color.Gray, shape)
            .padding(4.dp)
    ) {
        Box() {
            AsyncImage( // TODO: Lessen rounded corners
                model = pokemonCard.imageUri,
                contentDescription = pokemonCard.name,
                placeholder = painterResource(R.drawable.pokemon_card_back_side),
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(63.5f/88.9f)
            )
            val bgColor = if (pokemonCard.ownedQuantity < pokemonCard.targetQuantity) {
                Color(0xFFFF9800)
            } else {
                Color(0xFF8BC34A)
            }
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(4.dp)
                    .background(Color.Transparent)
                    .padding(2.dp)
                    .border(1.dp, Color.DarkGray, shape)
                    .background(bgColor, shape)
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${pokemonCard.ownedQuantity}/${pokemonCard.targetQuantity}",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = false)
@Composable
fun PokemonCardListItemPreview() {
    val previewHandler = AsyncImagePreviewHandler {
        ColorImage(Color.White.toArgb(), 640, 920)
    }
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        PokemonCardListItem(
            pokemonCard = sampleList.first(),
            onItemClick = { },
        )
    }
}
