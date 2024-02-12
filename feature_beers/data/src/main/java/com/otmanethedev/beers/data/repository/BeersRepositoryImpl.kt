package com.otmanethedev.beers.data.repository

import com.otmanethedev.beers.data.models.toDomain
import com.otmanethedev.beers.data.remote.BeerApi
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.repository.BeersRepository
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor(
    private val beerApi: BeerApi
) : BeersRepository {

    override suspend fun getBeers(): Result<List<Beer>> {
        return try {
            val response = beerApi.getBeers()
            val mappedResponse = response.map { it.toDomain() }
            Result.success(mappedResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}