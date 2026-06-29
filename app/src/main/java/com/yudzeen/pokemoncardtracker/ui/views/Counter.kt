package com.yudzeen.pokemoncardtracker.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yudzeen.pokemoncardtracker.R
import com.yudzeen.pokemoncardtracker.ui.theme.PokemonCardTrackerTheme

@Composable
fun Counter(label: String, value: Int, onValueChange: (Int) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier
                .padding(4.dp)
        )
        Button(
            onClick = {
                val newValue = value - 1
                onValueChange(newValue)
            },
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_remove),
                contentDescription = "Add"
            )
        }
        Text(
            text = value.toString(),
            modifier = Modifier
                .padding(4.dp)
        )
        Button(
            onClick = {
                val newValue = value + 1
                onValueChange(newValue)
            },
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = "Add"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    PokemonCardTrackerTheme {
        Counter(
            label = "Quantity",
            value = 5,
            onValueChange = {}
        )
    }
}
