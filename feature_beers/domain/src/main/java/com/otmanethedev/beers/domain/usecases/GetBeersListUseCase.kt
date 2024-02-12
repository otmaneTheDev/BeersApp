package com.otmanethedev.beers.domain.usecases

import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.repository.BeersRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetBeersListUseCase @Inject constructor(
    private val repository: BeersRepository
) {

    suspend operator fun invoke(): Result<List<Beer>> {
        return withContext(Dispatchers.IO) { repository.getBeers() }
    }
}