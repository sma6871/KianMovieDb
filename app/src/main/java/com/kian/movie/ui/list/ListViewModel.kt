package com.kian.movie.ui.list

import com.kian.movie.data.repository.Repository
import com.kian.movie.extensions.addToDisposable
import com.kian.movie.viewmodels.BaseViewModel
import io.reactivex.subjects.PublishSubject

class ListViewModel(val repository: Repository) : BaseViewModel() {

    val listActivityState = PublishSubject.create<ListActivityState>()

    fun initMovies(year: Int = -1) {
        //go to loading state
        listActivityState.onNext(Loading)
        addToDisposable {
            repository.discoverMovies(year).subscribe(
                {
                    // show result list or empty list if the result was null
                    listActivityState.onNext(SuccessLoading(it.results ?: listOf()))
                },
                {
                    listActivityState.onNext(ErrorLoading(it.localizedMessage ?: ""))
                }
            )
        }
    }

}