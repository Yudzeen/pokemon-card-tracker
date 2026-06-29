package com.yudzeen.pokemoncardtracker.core.repository

import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PokemonCardRepository {

    fun getAll(): Flow<List<PokemonCard>>
    suspend fun getById(id: UUID): PokemonCard
    suspend fun insert(pokemonCard: PokemonCard)
    suspend fun deleteById(id: UUID)
    suspend fun deleteByIds(ids: List<UUID>)

}