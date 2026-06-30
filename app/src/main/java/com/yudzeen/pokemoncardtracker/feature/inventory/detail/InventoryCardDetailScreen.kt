package com.yudzeen.pokemoncardtracker.feature.inventory.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.TopAppBarState
import com.yudzeen.pokemoncardtracker.core.model.sampleList
import com.yudzeen.pokemoncardtracker.ui.views.Counter

@Composable
fun InventoryCardDetailScreen(
    viewModel: InventoryCardDetailViewModel,
    updateTopAppBar: (TopAppBarState) -> Unit,
    onCardDeleted: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                InventoryCardDetailEvent.CardDeleted -> onCardDeleted()
            }
        }
    }
    InventoryCardDetailScreen(uiState, updateTopAppBar, viewModel::handleIntent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InventoryCardDetailScreen(
    uiState: InventoryCardDetailUiState,
    updateTopAppBarState: (TopAppBarState) -> Unit,
    handleIntent: (InventoryCardDetailIntent) -> Unit
) {
    uiState.pokemonCard?.let { pokemonCard ->
        updateTopAppBarState(TopAppBarState(
            title = pokemonCard.name,
            actions = { TopAppBarCardDetailActions { handleIntent(InventoryCardDetailIntent.DeleteCard) } }
        ))
        Column (
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = pokemonCard.imageUri,
                    contentDescription = pokemonCard.name,
                    placeholder = painterResource(R.drawable.pokemon_card_back_side),
                    modifier = Modifier
                        .height(200.dp)
                        .aspectRatio(63f/88f)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.Center)
                        .padding(2.dp)
                )
                IconButton(
                    onClick = { handleIntent(InventoryCardDetailIntent.ToggleFavorite) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    val drawableRes = if (uiState.pokemonCard.favorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outline
                    Icon(
                        painter = painterResource(drawableRes),
                        contentDescription = "Favorite",
                    )
                }
            }

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

@Composable
fun TopAppBarCardDetailActions(onDelete: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            painter = painterResource(R.drawable.ic_more_vertical),
            contentDescription = "More options"
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = {
                showMenu = false
                onDelete()
            }
        )
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