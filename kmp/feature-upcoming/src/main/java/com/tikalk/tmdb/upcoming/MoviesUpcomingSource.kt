package com.tikalk.tmdb.upcoming

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.source.TmdbDataSource

class MoviesUpcomingSource(private val dataSource: TmdbDataSource) : PagingSource<Int, MovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val nextPage = params.key ?: 1
            val response = dataSource.getMoviesUpcoming(page = nextPage, refresh = params is LoadParams.Refresh)!!
            val moviesPage = response.page
            LoadResult.Page(
                data = response.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (moviesPage.page < moviesPage.totalPages) response.page.page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        fun create(dataSource: TmdbDataSource) = MoviesUpcomingSource(dataSource)
    }
}