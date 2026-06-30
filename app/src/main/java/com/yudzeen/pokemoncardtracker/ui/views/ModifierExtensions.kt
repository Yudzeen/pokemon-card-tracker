package com.yudzeen.pokemoncardtracker.ui.views

import androidx.compose.ui.Modifier

fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

fun <T : Any> Modifier.ifNotNull(
    value: T?,
    builder: Modifier.(T) -> Modifier
): Modifier {
    return if (value != null) this.builder(value) else this
}