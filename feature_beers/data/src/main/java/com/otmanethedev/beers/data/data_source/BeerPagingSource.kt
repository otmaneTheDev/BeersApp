package com.otmanethedev.beers.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.otmanethedev.beers.data.data_source.errors.NoMoreResultsException
import com.otmanethedev.beers.data.models.toDomain
import com.otmanethedev.beers.data.remote.BeerApi
import com.otmanethedev.beers.domain.models.Beer
import java.io.IOException
import javax.inject.Inject

class BeerPagingSource @Inject constructor(
    private val api: BeerApi
) : PagingSource<Int, Beer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val pageIndex = params.key ?: ONE
        return try {
            val response = api.getBeers(page = pageIndex)
            val mappedResponse = response.map { it.toDomain() }

            if (mappedResponse.isNotEmpty()) {
                val nextKey = pageIndex + ONE

                LoadResult.Page(
                    data = mappedResponse,
                    prevKey = if (pageIndex == ONE) null else pageIndex - ONE,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(NoMoreResultsException)
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE)
        }
    }

    companion object {
        private const val ONE = 1
    }
}
