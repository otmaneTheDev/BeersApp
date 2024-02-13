package com.otmanethedev.beers.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.otmanethedev.beers.data.data_source.BeerPagingSource
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.repository.BeersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class BeersRepositoryImpl @Inject constructor(
    private val beerPagingSource: BeerPagingSource
) : BeersRepository {

    override fun getPaginatedBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { beerPagingSource }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 25
    }
}