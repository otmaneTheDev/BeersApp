package com.otmanethedev.beers.data.remote

import com.otmanethedev.beers.data.models.BeersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET("beers")
    suspend fun getBeers(@Query("page") page: Int): BeersResponseDto
}
