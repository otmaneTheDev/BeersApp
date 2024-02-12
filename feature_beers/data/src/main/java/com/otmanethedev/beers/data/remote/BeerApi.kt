package com.otmanethedev.beers.data.remote

import com.otmanethedev.beers.data.models.BeersResponseDto
import retrofit2.http.GET

interface BeerApi {

    @GET("beers")
    suspend fun getBeers(): BeersResponseDto
}
