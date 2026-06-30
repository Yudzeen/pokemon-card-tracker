package com.yudzeen.pokemoncardtracker.core.repository

import com.yudzeen.pokemoncardtracker.core.repository.database.dao.PokemonCardDao
import com.yudzeen.pokemoncardtracker.core.repository.database.entity.toEntity
import com.yudzeen.pokemoncardtracker.core.repository.database.entity.toModel
import com.yudzeen.pokemoncardtracker.core.model.PokemonCard
import com.yudzeen.pokemoncardtracker.core.repository.files.CardImageService
import com.yudzeen.pokemoncardtracker.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class PokemonCardRepositoryImpl @Inject constructor(
    private val pokemonCardDao: PokemonCardDao,
    private val cardImageService: CardImageService,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
): PokemonCardRepository {

    override fun getAll(): Flow<List<PokemonCard>> {
        return pokemonCardDao.getAll().map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: UUID): Flow<PokemonCard?> {
        return pokemonCardDao.getById(id).map { it?.toModel() }
    }

    override suspend fun insert(pokemonCard: PokemonCard) {
        val newImageUri = withContext(ioDispatcher) {
            cardImageService.saveCardImage(pokemonCard.imageUri, pokemonCard.id)
        }
        val updatedPokemonCard = pokemonCard.copy(imageUri = newImageUri)
        pokemonCardDao.insert(updatedPokemonCard.toEntity())
    }

    override suspend fun deleteById(id: UUID) {
        val deleted = withContext(ioDispatcher) {
            cardImageService.deleteCardImage(id)
        }
        if (!deleted) {
            throw IllegalStateException("Unable to delete image file of card: $id")
        }
        pokemonCardDao.deleteById(id)
    }

    override suspend fun deleteByIds(ids: List<UUID>) {
        pokemonCardDao.deleteByIds(ids)
    }

    override suspend fun updateOwnedQuantity(cardId: UUID, newValue: Int) {
        pokemonCardDao.updateOwnedQuantity(cardId, newValue)
    }

    override suspend fun updateTargetQuantity(cardId: UUID, newValue: Int) {
        pokemonCardDao.updateTargetQuantity(cardId, newValue)
    }

    override suspend fun toggleFavorite(cardId: UUID) {
        pokemonCardDao.toggleFavorite(cardId)
    }
}