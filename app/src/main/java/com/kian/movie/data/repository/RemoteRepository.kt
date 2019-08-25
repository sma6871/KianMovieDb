package com.kian.movie.data.repository

import com.kian.movie.data.models.MovieDiscoverResponse
import com.kian.movie.data.remote.AppService
import com.kian.movie.extensions.observeUiSubscribeIo
import io.reactivex.Single

class RemoteRepository(private val appService: AppService) {
    fun discoverMovies(year: Int): Single<MovieDiscoverResponse> {
        return appService.discoverMovies(year).observeUiSubscribeIo()
    }
}