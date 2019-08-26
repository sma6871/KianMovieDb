package com.kian.movie.data.remote

import com.kian.movie.data.models.MovieDiscoverResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("discover/movie")
    fun discoverMovies(
        @Query("primary_release_year") year: Int,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean = false
    ): Single<MovieDiscoverResponse>


}