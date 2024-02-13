package com.otmanethedev.beers.domain.repository

import androidx.paging.PagingData
import com.otmanethedev.beers.domain.models.Beer
import kotlinx.coroutines.flow.Flow

interface BeersRepository {
    fun getPaginatedBeers(): Flow<PagingData<Beer>>
}