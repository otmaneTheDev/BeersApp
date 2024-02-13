package com.otmanethedev.beers.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.usecases.GetPaginatedBeerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

@HiltViewModel
class BeerListViewModel @Inject constructor(
    getPaginatedBeerListUseCase: GetPaginatedBeerListUseCase
) : ViewModel() {

    sealed class BeerListAction {
        class FilterByName(val name: String) : BeerListAction()
    }

    private var _beers: Flow<PagingData<Beer>> = getPaginatedBeerListUseCase().cachedIn(viewModelScope)
    private var _query: MutableStateFlow<String> = MutableStateFlow("")

    private val query: StateFlow<String> = _query.asStateFlow()

    val beers: Flow<PagingData<Beer>> = combine(_beers, query) { beers, query ->
        beers.filter { beer -> beer.name.contains(query, true) }
    }.cachedIn(viewModelScope)

    fun handleAction(action: BeerListAction) {
        when (action) {
            is BeerListAction.FilterByName -> handleFilterByName(action.name)
        }
    }

    private fun handleFilterByName(input: String) {
        _query.value = input
    }
}