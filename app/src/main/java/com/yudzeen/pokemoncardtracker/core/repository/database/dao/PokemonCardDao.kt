package com.yudzeen.pokemoncardtracker.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.yudzeen.pokemoncardtracker.core.repository.database.entity.PokemonCardEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PokemonCardDao {

    @Query("SELECT * FROM pokemon_card")
    fun getAll(): Flow<List<PokemonCardEntity>>

    @Query("SELECT * FROM pokemon_card WHERE id = :id")
    fun getById(id: UUID): Flow<PokemonCardEntity>

    @Insert
    suspend fun insert(pokemonCard: PokemonCardEntity)

    @Upsert
    suspend fun upsert(pokemonCard: PokemonCardEntity)

    @Query("DELETE FROM pokemon_card WHERE id = :id")
    suspend fun deleteById(id: UUID)

    @Query("DELETE FROM pokemon_card WHERE id in (:ids)")
    suspend fun deleteByIds(ids: List<UUID>)

    @Query("UPDATE pokemon_card SET owned_quantity = :newValue WHERE id = :cardId")
    suspend fun updateOwnedQuantity(cardId: UUID, newValue: Int)

    @Query("UPDATE pokemon_card SET target_quantity = :newValue WHERE id = :cardId")
    suspend fun updateTargetQuantity(cardId: UUID, newValue: Int)

    @Query("UPDATE pokemon_card SET favorite = NOT favorite WHERE id = :cardId")
    suspend fun toggleFavorite(cardId: UUID)

}