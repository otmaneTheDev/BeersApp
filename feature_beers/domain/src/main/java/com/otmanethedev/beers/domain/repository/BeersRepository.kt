package com.otmanethedev.beers.domain.repository

import com.otmanethedev.beers.domain.models.Beer

interface BeersRepository {
    suspend fun getBeers(): Result<List<Beer>>
}