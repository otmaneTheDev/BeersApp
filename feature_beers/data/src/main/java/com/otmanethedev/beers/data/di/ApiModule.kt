package com.otmanethedev.beers.data.di

import com.otmanethedev.beers.data.remote.BeerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideBeerApi(okHttpClient: OkHttpClient): BeerApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.punkapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(BeerApi::class.java)
    }
}