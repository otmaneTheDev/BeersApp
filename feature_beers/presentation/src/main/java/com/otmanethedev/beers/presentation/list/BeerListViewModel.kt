package com.otmanethedev.beers.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.usecases.GetBeersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val getBeersListUseCase: GetBeersListUseCase
) : ViewModel() {

    sealed class BeerListUiState {
        data object Idle : BeerListUiState()
        data object Loading : BeerListUiState()
        class Error(val message: String) : BeerListUiState()
        class Success(val beers: List<Beer>) : BeerListUiState()
    }

    private val _uiState: MutableStateFlow<BeerListUiState> = MutableStateFlow(BeerListUiState.Idle)
    val uiState: StateFlow<BeerListUiState> get() = _uiState.asStateFlow()

    init {
        fetchBeers()
    }

    private fun fetchBeers() {
        viewModelScope.launch {
            _uiState.value = BeerListUiState.Loading
            getBeersListUseCase().onSuccess {
                _uiState.value = BeerListUiState.Success(it)
            }.onFailure {
                _uiState.value = BeerListUiState.Error(it.message.orEmpty())
            }
        }
    }
}