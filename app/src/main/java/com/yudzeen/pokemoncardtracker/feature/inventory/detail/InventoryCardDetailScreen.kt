package com.yudzeen.pokemoncardtracker.feature.inventory.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.core.model.sampleList
import com.yudzeen.pokemoncardtracker.ui.views.Counter

@Composable
fun InventoryCardDetailScreen(viewModel: InventoryCardDetailViewModel, onTitleChanged: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    InventoryCardDetailScreen(uiState, onTitleChanged, viewModel::handleIntent)
}

@Composable
internal fun InventoryCardDetailScreen(
    uiState: InventoryCardDetailUiState,
    onTitleChanged: (String) -> Unit,
    handleIntent: (InventoryCardDetailIntent) -> Unit
) {
    uiState.pokemonCard?.let { pokemonCard ->
        onTitleChanged(pokemonCard.name)
        Column (
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = pokemonCard.imageUri,
                contentDescription = pokemonCard.name,
                placeholder = painterResource(R.drawable.pokemon_card_back_side),
                modifier = Modifier
                    .height(200.dp)
                    .aspectRatio(63f/88f)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Text("Name: ${pokemonCard.name}")
            Counter(
                label = "Owned:",
                value = pokemonCard.ownedQuantity,
                onValueChange = { handleIntent(InventoryCardDetailIntent.UpdateOwnedQuantity(it)) }
            )
            Counter(
                label = "Target:",
                value = pokemonCard.targetQuantity,
                onValueChange = { handleIntent(InventoryCardDetailIntent.UpdateTargetQuantity(it)) }
            )
            Text("Series: ${pokemonCard.series}")
            Text("Expansion: ${pokemonCard.expansion}")
            Text("Variant: ${pokemonCard.variant}")
            Text("Category: ${pokemonCard.category}")
        }

    } ?: run {
        // TODO: Update to error screen and loading screen
//        val context = LocalContext.current
//        Toast.makeText(context, "Unable to load pokemon card.", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryCardDetailScreenPreview() {
    val uiState = InventoryCardDetailUiState(
        pokemonCard = sampleList.first()
    )
    InventoryCardDetailScreen(uiState, { }, { })
}