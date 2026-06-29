package com.yudzeen.pokemoncardtracker.feature.inventory.add

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.ui.views.DropdownField
import com.yudzeen.pokemoncardtracker.ui.views.NumberPicker

@Composable
fun InventoryAddCardScreen(viewModel: InventoryAddCardViewModel, navigateToCardDetail: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is InventoryAddCardUiEvent.AddCardSuccess -> navigateToCardDetail(event.cardId.toString())
                InventoryAddCardUiEvent.AddCardError -> Toast.makeText(context, "Add card error.", Toast.LENGTH_LONG).show()
            }
        }
    }

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
    val scrollState = rememberScrollState()
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { handleIntent(InventoryAddCardIntent.UpdatePhotoUri(it.toString())) }
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            if (uiState.photoUri != null) {
                AsyncImage(
                    model = uiState.photoUri,
                    contentDescription = "Pokemon Card Photo",
                    placeholder = painterResource(R.drawable.pokemon_card_back_side),
                    modifier = Modifier
                        .height(200.dp)
                        .aspectRatio(63f / 88f)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.Center)
                        .padding(2.dp)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.pokemon_card_back_side_grayscale),
                    contentDescription = "Upload Photo",
                    modifier = Modifier
                        .height(200.dp)
                        .aspectRatio(63f / 88f)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.Center)
                        .padding(2.dp)
                )
            }
            IconButton(
                onClick = { photoPickerLauncher.launch(PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_photo),
                    contentDescription = "Upload Photo",
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            CardNameField(
                cardName = uiState.cardName,
                onValueChange = { handleIntent(InventoryAddCardIntent.UpdateCardName(it)) },
                isError = uiState.errors.contains(InventoryAddCardError.NoCardNameIndicated),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
//            PhotoUriField(
//                photoUri = uiState.photoUri ?: "",
//                onValueChange = { handleIntent(InventoryAddCardIntent.UpdatePhotoUri(it)) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            )
            Row(modifier = Modifier.fillMaxWidth()) {
                OwnedQuantityField(
                    quantity = uiState.ownedQuantity,
                    onValueChange = { handleIntent(InventoryAddCardIntent.UpdateOwnedQuantity(it)) },
                    modifier = Modifier.weight(1f)
                )
                TargetQuantityField(
                    quantity = uiState.targetQuantity,
                    onValueChange = { handleIntent(InventoryAddCardIntent.UpdateTargetQuantity(it)) },
                    modifier = Modifier.weight(1f)
                )
            }

            DropdownField(
                label = "Series",
                selectedOption = uiState.selectedSeries,
                options = uiState.seriesOptions,
                onValueChange = { handleIntent(InventoryAddCardIntent.UpdateSelectedSeries(it)) },
                isError = uiState.errors.contains(InventoryAddCardError.NoSeriesSelected),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            DropdownField(
                label = "Expansion",
                selectedOption = uiState.selectedExpansion,
                options = uiState.expansionOptions,
                onValueChange = { handleIntent(InventoryAddCardIntent.UpdateSelectedExpansion(it)) },
                isError = uiState.errors.contains(InventoryAddCardError.NoExpansionSelected),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            DropdownField(
                label = "Variant",
                selectedOption = uiState.selectedVariant,
                options = uiState.variantOptions,
                onValueChange = { handleIntent(InventoryAddCardIntent.UpdateSelectedVariant(it)) },
                isError = uiState.errors.contains(InventoryAddCardError.NoVariantSelected),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            DropdownField(
                label = "Category",
                selectedOption = uiState.selectedCategory,
                options = uiState.categoryOptions,
                onValueChange = { handleIntent(InventoryAddCardIntent.UpdateSelectedCategory(it)) },
                isError = uiState.errors.contains(InventoryAddCardError.NoCategorySelected),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
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
fun CardNameField(
    cardName: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = cardName,
        onValueChange = onValueChange,
        label = { Text("Card Name") },
        placeholder = { Text("Enter card name") },
        singleLine = true,
        isError = isError,
        modifier = modifier
    )
}

@Composable
fun PhotoUriField(
    photoUri: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = photoUri,
        onValueChange = onValueChange,
        label = { Text("Photo Uri") },
        placeholder = { Text("Enter photo uri") },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun OwnedQuantityField(quantity: Int, onValueChange: (Int) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.wrapContentSize()) {
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
fun TargetQuantityField(quantity: Int, onValueChange: (Int) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.wrapContentSize()) {
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