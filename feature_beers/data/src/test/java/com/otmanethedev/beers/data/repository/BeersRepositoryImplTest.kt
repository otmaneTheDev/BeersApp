package com.otmanethedev.beers.data.repository

import com.otmanethedev.beers.data.models.BeersResponseDto
import com.otmanethedev.beers.data.remote.BeerApi
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BeersRepositoryImplTest {

    private lateinit var beersRepository: BeersRepositoryImpl

    private val beerApi: BeerApi = mockk()

    @Before
    fun setUp() {
        beersRepository = BeersRepositoryImpl(beerApi)
    }

    @Test
    fun `when beersRepository getBeers then check success`() = runBlocking {
        // With
        val mockResponse = BeersResponseDto()
        coEvery { beerApi.getBeers() } returns mockResponse

        // When
        val result = beersRepository.getBeers()

        // Then
        assertTrue(result.isSuccess)
    }

    @Test
    fun `when beersRepository getBeers then check failure`() = runBlocking {
        // With
        coEvery { beerApi.getBeers() } throws Exception("API error")

        // When
        val result = beersRepository.getBeers()

        // Then
        assertTrue(result.isFailure)
    }
}
