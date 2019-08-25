package com.kian.movie.di

import com.kian.movie.data.repository.Repository
import com.kian.movie.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get() as Repository) }

}