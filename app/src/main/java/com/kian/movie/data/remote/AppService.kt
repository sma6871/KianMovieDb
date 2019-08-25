package com.kian.movie.data.remote

import io.reactivex.Completable
import retrofit2.http.GET

interface AppService {

    @GET("sample")
    fun sampleApi(): Completable


}