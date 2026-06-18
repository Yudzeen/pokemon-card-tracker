package com.yudzeen.pokemoncardtracker.ui.views

import android.widget.NumberPicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NumberPicker(
    value: Int,
    minValue: Int,
    maxValue: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            NumberPicker(context).apply {
                this.minValue = minValue
                this.maxValue = maxValue
                this.value = value
                setOnValueChangedListener { _, _, newValue ->
                    onValueChange(newValue)
                }
            }
        },
        update = { view ->
            view.value = value
            view.minValue = minValue
            view.maxValue = maxValue
        }
    )

}