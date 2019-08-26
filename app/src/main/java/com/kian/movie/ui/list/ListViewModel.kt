package com.kian.movie.ui.list

import com.kian.movie.data.repository.Repository
import com.kian.movie.extensions.addToDisposable
import com.kian.movie.viewmodels.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class ListViewModel(val repository: Repository) : BaseViewModel() {

    val listActivityState = BehaviorSubject.create<ListActivityState>()
    private var totalPages = 0
    private var selectedYear = -1

    fun initMovies(year: Int = -1) {
        selectedYear = year
        //go to loading state
        listActivityState.onNext(Loading)
        addToDisposable {
            repository.discoverMovies(year).subscribe(
                {
                    totalPages = it.totalPages ?: 0
                    // show result list or empty list if the result was null
                    listActivityState.onNext(SuccessLoading(it.results ?: listOf()))
                },
                {
                    listActivityState.onNext(ErrorLoading(it.localizedMessage ?: ""))
                    listActivityState.onNext(Init)
                }
            )
        }
    }

    fun loadMore(newPageIndex: Int) {
        if (totalPages >= newPageIndex) {
            listActivityState.onNext(LoadingMore)
            addToDisposable {
                repository.discoverMovies(selectedYear, newPageIndex).subscribe(
                    {
                        totalPages = it.totalPages ?: 0
                        // show result list or empty list if the result was null
                        listActivityState.onNext(SuccessLoadingMore(it.results ?: listOf()))
                    },
                    {
                        listActivityState.onNext(ErrorLoading(it.localizedMessage ?: ""))
                        listActivityState.onNext(Init)
                    }
                )
            }
        }
    }

}