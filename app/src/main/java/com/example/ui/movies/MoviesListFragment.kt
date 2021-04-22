package com.example.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.networking.response.similarmovies.Result
import com.example.data.repository.MoviesRepository
import com.example.dialogerror.DialogErrorException
import com.example.ui.R
import com.example.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movies_list.*
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MoviesListFragment : Fragment(), MoviesListContract.View,
    MoviesListAdapter.ItemMovieOnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: MoviesListPresenter
    private lateinit var adapter: MoviesListAdapter
    private lateinit var repository: MoviesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        repository = MoviesRepository()
        presenter = MoviesListPresenter(this, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
            lifecycleScope.launch {
                try {
                    val responseMainMovie = presenter.getMainMovie()
                    val responseSimilarMovies = presenter.getSimilarMovies()
                    Picasso.get().load(AppConstants.BASE_URL_PHOTO + responseMainMovie?.backdrop_path).into(mainMovieLogo)
                    likesMainMovieTv.text = responseMainMovie?.vote_count.toString()
                    popularutyTv.text = responseMainMovie?.popularity.toString()
                    mainMovieTitle.text = responseMainMovie?.title
                    adapter = MoviesListAdapter(responseSimilarMovies!!, object : MoviesListAdapter.ItemMovieOnItemClickListener{
                        override fun onItemClicked(item: Result) {
                            goToDetailsMovie(item)
                        }
                    })
                    configRecyclerView(adapter)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    override fun configRecyclerView(adapter: MoviesListAdapter) {
        recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun goToDetailsMovie(result: Result) {
        val bundle = bundleOf("result" to result)
        findNavController().navigate(R.id.action_moviesListFragment_to_detailsMovieFragment, bundle)
    }

    override fun showLoading(success: Boolean) {
        if (success) movieScreenPb.visibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showErrorException(error: String) {
        DialogErrorException(
            context = requireContext(),
            titleError = error,
            mensageErro = error
        ) {
            initView()
        }.show()
    }

    override fun onItemClicked(item: Result) {
        Toast.makeText(requireContext(), item.title, Toast.LENGTH_LONG).show()
    }
}