package com.example.ui.movies

import com.example.data.networking.response.main.movie.MovieResponse
import com.example.data.networking.response.similarmovies.SimilarMoviesResponse
import com.example.data.repository.MoviesRepository

class MoviesListPresenter(private val view: MoviesListContract.View, private val repository: MoviesRepository) :
    MoviesListContract.Presenter {
    override suspend fun getMainMovie(): MovieResponse? {
        try {
            val response = repository.getMainMovie()
            if (response.isSuccessful) {
                view.showLoading(success = true)
                return response.body()
            } else {
                view.showError(response.message().toString())
            }
        } catch (e: Exception) {
            view.showErrorException(e.message.toString())
        }
        return null
    }

    override suspend fun getSimilarMovies(): SimilarMoviesResponse? {
        try {
            val response = repository.getSimilarMovies()
            if (response.isSuccessful) {
                return response.body()
            } else {
                view.showError(response.message().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}