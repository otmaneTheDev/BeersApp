package com.otmanethedev.beers.data.di

import com.otmanethedev.beers.data.repository.BeersRepositoryImpl
import com.otmanethedev.beers.domain.repository.BeersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideRepository(repository: BeersRepositoryImpl): BeersRepository
}