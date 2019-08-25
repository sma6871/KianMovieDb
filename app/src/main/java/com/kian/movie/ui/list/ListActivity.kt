package com.kian.movie.ui.list

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kian.movie.R
import com.kian.movie.data.models.MovieItem
import com.kian.movie.extensions.plusAssign
import com.kian.movie.extensions.showHide
import com.kian.movie.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : BaseActivity() {

    private val viewModel: ListViewModel by viewModel()
    private var adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        observeState()
        initUi()

        if (savedInstanceState == null) {
            viewModel.initMovies()
        }

    }

    private fun initUi() {
        listMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        listMovies.adapter = adapter
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
        showLoading(false)
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun showList(movies: List<MovieItem>) {
        showLoading(false)
        adapter.updateData(movies)
    }

    private fun showLoading(isShow: Boolean) {
        loading.showHide(isShow)
    }
}
