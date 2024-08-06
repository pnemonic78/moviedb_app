package com.tikalk.tmdb.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPage
import com.tikalk.tmdb.page550

internal class MoviesPreviewSource() : PagingSource<Int, MovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val nextPage = params.key ?: 1
            val response: MoviesPage = page550
            val moviesPage: MoviesPage = response
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (moviesPage.page < moviesPage.totalPages) response.page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}