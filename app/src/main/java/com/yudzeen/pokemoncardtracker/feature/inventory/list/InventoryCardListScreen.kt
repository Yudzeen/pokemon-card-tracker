package com.yudzeen.pokemoncardtracker.feature.inventory.list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.core.model.seriesToCardListMap
import com.yudzeen.pokemoncardtracker.feature.inventory.list.views.PokemonCardList

@Composable
fun InventoryCardListScreen(viewModel: InventoryCardListViewModel, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    InventoryCardListScreen(uiState, onItemClick, modifier)
}

@Composable
internal fun InventoryCardListScreen(uiState: InventoryCardListUiState, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        PokemonCardList(uiState.seriesToCardListMap, onItemClick)

        FloatingActionButton(
            onClick = {
                Toast.makeText(context, "Fab Clicked.", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(painterResource(R.drawable.ic_add), "Floating action button.")
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun InventoryCardListScreenPreview() {
    InventoryCardListScreen(
        uiState = InventoryCardListUiState(seriesToCardListMap),
        onItemClick = { }
    )
}