package com.otmanethedev.beers.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.otmanethedev.beers.domain.models.Beer
import com.otmanethedev.beers.domain.usecases.GetBeersListUseCase
import com.otmanethedev.beers.presentation.list.BeerListViewModel
import com.otmanethedev.beers.presentation.list.BeerListViewModel.BeerListUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BeerListViewModelTest {

    private lateinit var viewModel: BeerListViewModel

    @Mock
    private lateinit var getBeersListUseCase: GetBeersListUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `with viewModel updates uiState to Idle, Loading then Success when use case returns success`() = runTest {
        // With
        val mockBeers = listOf(
            Beer(1, "Beer 1", "", "", "", 2.4f, 2f, listOf(), listOf()),
            Beer(2, "Beer 2", "", "", "", 2.4f, 2f, listOf(), listOf()),
        )

        doReturn(Result.success(mockBeers)).`when`(getBeersListUseCase).invoke()

        // When
        viewModel = BeerListViewModel(getBeersListUseCase)

        // Then
        viewModel.uiState.test {
            assertEquals(BeerListUiState.Idle, awaitItem())

            assertEquals(BeerListUiState.Loading, awaitItem())
            val successItem = awaitItem() as BeerListUiState.Success
            assertEquals(mockBeers, successItem.beers)
        }
    }

    @Test
    fun `with viewModel updates uiState to Idle, Loading then Error when use case returns failure`() = runTest {
        // With
        val exception = Throwable("Error fetching beers")
        doReturn(Result.failure<List<Beer>>(exception)).`when`(getBeersListUseCase).invoke()

        // When
        viewModel = BeerListViewModel(getBeersListUseCase)

        // Then
        viewModel.uiState.test {
            assertEquals(BeerListUiState.Idle, awaitItem())

            assertEquals(BeerListUiState.Loading, awaitItem())
            val successItem = awaitItem() as BeerListUiState.Error
            assertEquals(exception.message, successItem.message)
        }
    }
}
