package com.otmanethedev.beers.domain.usecases

import androidx.paging.PagingData
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.repository.BeersRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetPaginatedBeerListUseCase @Inject constructor(
    private val beersRepository: BeersRepository
) {

     operator fun invoke(): Flow<PagingData<Beer>> {
        return beersRepository.getPaginatedBeers().flowOn(Dispatchers.IO)
    }
}
