package com.yudzeen.pokemoncardtracker.core.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindPokemonCardRepository(pokemonCardRepositoryImpl: PokemonCardRepositoryImpl): PokemonCardRepository

}