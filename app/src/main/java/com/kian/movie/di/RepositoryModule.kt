package com.kian.movie.di

import com.kian.movie.data.remote.AppService
import com.kian.movie.data.repository.LocalRepository
import com.kian.movie.data.repository.RemoteRepository
import com.kian.movie.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module()
{
    single {
        Repository(
            get() as RemoteRepository,
            get() as LocalRepository
        )
    }

    single { RemoteRepository(get() as AppService) }
    single { LocalRepository() }

}