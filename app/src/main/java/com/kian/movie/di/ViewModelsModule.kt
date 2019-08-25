package com.kian.movie.di

import com.kian.movie.data.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get() as Repository) }

}