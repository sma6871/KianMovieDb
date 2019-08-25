package com.kian.movie.ui.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity : AppCompatActivity() {
    val bag: CompositeDisposable = CompositeDisposable()


    override fun onDestroy() {
        super.onDestroy()
        bag.clear()
    }
}