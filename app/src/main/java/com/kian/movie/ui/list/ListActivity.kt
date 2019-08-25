package com.kian.movie.ui.list

import android.os.Bundle
import com.kian.movie.R
import com.kian.movie.data.models.MovieItem
import com.kian.movie.extensions.plusAssign
import com.kian.movie.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : BaseActivity() {

    val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        observeState()

        if (savedInstanceState == null) {
            viewModel.initMovies()
        }

    }

    private fun observeState() {
        bag += viewModel.listActivityState.subscribe {
            when (it) {
                Loading -> showLoading(true)
                is SuccessLoading -> showList(it.movies)
                is ErrorLoading -> showError(it.message)
            }
        }
    }

    private fun showError(errorMessage: String) {
        //TODO: show error message/retry if needed
    }

    private fun showList(movies: List<MovieItem>) {
        //TODO: show list of movies
    }

    private fun showLoading(isShow: Boolean) {
        //TODO: show or hide loading indicator
    }
}
