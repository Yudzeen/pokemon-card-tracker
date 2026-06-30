package com.yudzeen.pokemoncardtracker.core.repository

import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PokemonCardRepository {

    fun getAll(): Flow<List<PokemonCard>>
    fun getById(id: UUID): Flow<PokemonCard?>
    suspend fun insert(pokemonCard: PokemonCard)
    suspend fun deleteById(id: UUID)
    suspend fun deleteByIds(ids: List<UUID>)
    suspend fun updateOwnedQuantity(cardId: UUID, newValue: Int)
    suspend fun updateTargetQuantity(cardId: UUID, newValue: Int)
    suspend fun toggleFavorite(cardId: UUID)

}