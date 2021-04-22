package com.example.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.data.networking.response.similarmovies.Result
import com.example.ui.R
import com.example.utils.AppConstants
import com.example.utils.ToGenres
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details_movie.*

class DetailsMovieFragment : Fragment(), ContractDetailsMovie.View {
    private var detailMovie: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovie =  arguments?.getSerializable("result") as Result

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        Picasso.get().load(AppConstants.BASE_URL_PHOTO + detailMovie?.backdrop_path).into(detailMovieIg)
        detailMovieTitleTv.text = detailMovie?.title
        detailMovieDescriptionTv.text = detailMovie?.overview
        detailMovieGenreTv.text = getGenre(detailMovie!!)
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
}