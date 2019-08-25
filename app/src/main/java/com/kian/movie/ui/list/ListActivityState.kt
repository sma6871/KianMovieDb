package com.kian.movie.ui.list

import com.kian.movie.data.models.MovieItem

sealed class ListActivityState
object Init : ListActivityState()
object Loading : ListActivityState()
data class SuccessLoading(val movies: List<MovieItem>) : ListActivityState()
data class ErrorLoading(val message:String) : ListActivityState()