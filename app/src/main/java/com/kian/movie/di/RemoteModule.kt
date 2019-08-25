package com.kian.movie.di

import android.content.Context
import com.kian.movie.R
import com.kian.movie.data.remote.AppService
import com.kian.movie.data.remote.AuthenticatorInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module(createdAtStart = true) {

    // provided web components
    single { createOkHttpClient(androidContext(), get() as AuthenticatorInterceptor) }

    single { AuthenticatorInterceptor() }

    single { GsonConverterFactory.create(get()) }

    single {
        createWebService<AppService>(
            get() as OkHttpClient,
            get() as GsonConverterFactory,
            androidContext().getString(R.string.base_url)
        )
    }

}


fun createOkHttpClient(context: Context, authInterceptor: AuthenticatorInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(ChuckInterceptor(context))
        .addInterceptor(authInterceptor)
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}