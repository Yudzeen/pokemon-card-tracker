package com.yudzeen.pokemoncardtracker.core.repository.files

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface FilesModule {

    @Singleton
    @Binds
    fun bindCardImageService(cardImageServiceImpl: CardImageServiceImpl): CardImageService

}