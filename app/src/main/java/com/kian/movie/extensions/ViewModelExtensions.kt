package com.kian.movie.extensions

import com.kian.movie.viewmodels.BaseViewModel
import io.reactivex.disposables.Disposable

fun BaseViewModel.addToDisposable(disposable: () -> Disposable) {
    compositeDisposable += disposable.invoke()
}
