package com.yudzeen.pokemoncardtracker.core.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.yudzeen.pokemoncardtracker.core.repository.database.entity.PokemonCardEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PokemonCardDao {

    @Query("SELECT * FROM pokemon_card")
    fun getAll(): Flow<List<PokemonCardEntity>>

    @Query("SELECT * FROM pokemon_card WHERE id = :id LIMIT 1")
    suspend fun getById(id: UUID): PokemonCardEntity

    @Insert
    suspend fun insert(pokemonCard: PokemonCardEntity)

    @Upsert
    suspend fun upsert(pokemonCard: PokemonCardEntity)

    @Query("DELETE FROM pokemon_card WHERE id = :id")
    suspend fun deleteById(id: UUID)

    @Query("DELETE FROM pokemon_card WHERE id in (:ids)")
    suspend fun deleteByIds(ids: List<UUID>)

}