package com.example.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.networking.response.similarmovies.Result
import com.example.data.networking.response.similarmovies.SimilarMoviesResponse
import com.example.ui.databinding.ItemRecyclerViewBinding
import com.example.utils.AppConstants
import com.example.utils.ToGenres
import com.squareup.picasso.Picasso

class MoviesListAdapter(
    private val list: SimilarMoviesResponse,
    private val itemMovieOnItemClickListener: ItemMovieOnItemClickListener
) : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>() {

    interface ItemMovieOnItemClickListener {
        fun onItemClicked(item: Result)
    }

    class MoviesViewHolder private constructor(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(result: Result) {
            binding.movieTitle.text = result.title
            binding.movieDescription.text = getGenre(result)
            Picasso.get().load(AppConstants.BASE_URL_PHOTO + result.backdrop_path)
                .into(binding.movieLogo)
        }

        companion object {
            fun from(parent: ViewGroup): MoviesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return MoviesViewHolder(binding)
            }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list.results[position])
        holder.itemView.setOnClickListener {
            itemMovieOnItemClickListener.onItemClicked(list.results[position])
        }
    }

    override fun getItemCount(): Int {
        return list.results.size
    }
}

private fun getGenre(similarMovie: Result): String {
    val genreIds = similarMovie.genre_ids
    val year = similarMovie.release_date.take(4)
    var genres = ""
    genreIds.forEach {
        genres += "${ToGenres.ToGenres.invoke(it)}, "
    }
    return "$year ${genres.subSequence(0, genres.count() - 2)}"
}