package com.yudzeen.pokemoncardtracker.core.repository.database.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yudzeen.pokemoncardtracker.core.model.Category
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.model.Series
import com.yudzeen.pokemoncardtracker.core.model.Variant
import java.util.UUID

@Entity(tableName = "pokemon_card")
data class PokemonCardEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val series: Series,
    val expansion: Series.Expansion,
    val category: Category,
    val variant: Variant,
    @ColumnInfo(name = "owned_quantity") val ownedQuantity: Int,
    @ColumnInfo(name = "target_quantity") val targetQuantity: Int,
    val favorite: Boolean,
    @ColumnInfo(name = "image_uri") val imageUri: Uri
)

fun PokemonCard.toEntity(): PokemonCardEntity {
    return PokemonCardEntity(
        id = id,
        name = name,
        series = series,
        expansion = expansion,
        category = category,
        variant = variant,
        ownedQuantity = ownedQuantity,
        targetQuantity = targetQuantity,
        favorite = favorite,
        imageUri = imageUri
    )
}

fun PokemonCardEntity.toModel(): PokemonCard {
    return PokemonCard(
        id = id,
        name = name,
        series = series,
        expansion = expansion,
        category = category,
        variant = variant,
        ownedQuantity = ownedQuantity,
        targetQuantity = targetQuantity,
        favorite = favorite,
        imageUri = imageUri
    )
}