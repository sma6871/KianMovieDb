package com.kian.movie.data.repository

import com.kian.movie.data.models.MovieDiscoverResponse
import io.reactivex.Single

class Repository(
    private val remoteRepo: RemoteRepository,
    private val localRepository: LocalRepository
) {
    fun discoverMovies(year: Int): Single<MovieDiscoverResponse> {
        return remoteRepo.discoverMovies(year)
    }

}