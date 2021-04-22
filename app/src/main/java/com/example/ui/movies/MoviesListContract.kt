package com.example.ui.movies

import com.example.data.networking.response.main.movie.MovieResponse
import com.example.data.networking.response.similarmovies.Result
import com.example.data.networking.response.similarmovies.SimilarMoviesResponse

interface MoviesListContract {
    interface View{
        fun initView()
        fun showLoading(success: Boolean)
        fun showError(error: String)
        fun showErrorException(error: String)
        fun configRecyclerView(adapter: MoviesListAdapter)
        fun goToDetailsMovie(result: Result)
    }

    interface Presenter{
        suspend fun getMainMovie(): MovieResponse?
        suspend fun getSimilarMovies(): SimilarMoviesResponse?
    }
}