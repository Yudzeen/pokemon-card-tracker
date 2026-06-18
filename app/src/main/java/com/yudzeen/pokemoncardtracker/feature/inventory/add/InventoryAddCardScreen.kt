package com.yudzeen.pokemoncardtracker.feature.inventory.add

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.ui.views.DropdownField
import com.yudzeen.pokemoncardtracker.ui.views.NumberPicker

@Composable
fun InventoryAddCardScreen(viewModel: InventoryAddCardViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    InventoryAddCardScreen(
        uiState = uiState,
        handleIntent = viewModel::handleIntent
    )
}

@Composable
internal fun InventoryAddCardScreen(
    uiState: InventoryAddCardViewState,
    handleIntent: (InventoryAddCardIntent) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(R.drawable.pokemon_card_back_side_grayscale),
                contentDescription = "Upload Photo",
                modifier = Modifier
                    .height(200.dp)
                    .aspectRatio(63f / 88f)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.Center)
            )
            IconButton(
                onClick = { },   // TODO: Upload Photo
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_photo),
                    contentDescription = "Upload Photo",
                )
            }
        }

        CardNameField(
            cardName = uiState.cardName,
            onValueChange = { handleIntent(InventoryAddCardIntent.UpdateCardName(it)) }
        )
        OwnedQuantityField(
            quantity = uiState.ownedQuantity,
            onValueChange = { handleIntent(InventoryAddCardIntent.UpdateOwnedQuantity(it)) }
        )
        TargetQuantityField(
            quantity = uiState.targetQuantity,
            onValueChange = { handleIntent(InventoryAddCardIntent.UpdateTargetQuantity(it)) }
        )
        DropdownField(
            selectedOption = uiState.selectedSeries,
            options = uiState.seriesOptions,
            onValueChange = { handleIntent(InventoryAddCardIntent.UpdateSelectedSeries(it)) }
        )
        DropdownField(
            selectedOption = uiState.selectedExpansion,
            options = uiState.expansionOptions,
            onValueChange = { handleIntent(InventoryAddCardIntent.UpdateSelectedExpansion(it)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryAddCardScreenPreview() {
    val uiState = InventoryAddCardViewState()
    InventoryAddCardScreen(
        uiState = uiState,
        handleIntent = { }
    )
}

@Composable
fun CardNameField(cardName: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = cardName,
        onValueChange = onValueChange,
        label = { Text("Card Name") },
        placeholder = { Text("Enter card name") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun OwnedQuantityField(quantity: Int, onValueChange: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Owned",
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )
        NumberPicker(
            value = quantity,
            minValue = 0,
            maxValue = 99,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun TargetQuantityField(quantity: Int, onValueChange: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Target",
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )
        NumberPicker(
            value = quantity,
            minValue = 0,
            maxValue = 99,
            onValueChange = onValueChange
        )
    }
}