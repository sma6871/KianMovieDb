package com.kian.movie

import android.app.Application
import com.kian.movie.di.remoteModule
import com.kian.movie.di.repositoryModule
import com.kian.movie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KianApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KianApplication)
            this.modules(
                remoteModule +
                        viewModelModule +
                        repositoryModule
            )
        }
    }

}