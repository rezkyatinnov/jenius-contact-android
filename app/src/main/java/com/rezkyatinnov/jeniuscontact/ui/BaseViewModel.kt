package com.rezkyatinnov.jeniuscontact.ui

import androidx.lifecycle.ViewModel
import com.rezkyatinnov.jeniuscontact.restapi.ApiServices
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailViewModel
import com.rezkyatinnov.jeniuscontact.ui.main.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


abstract class BaseViewModel(var baseActivity: BaseActivity):ViewModel(){

    @Inject
    lateinit var apiServices: ApiServices

    val disposables = CompositeDisposable()

    init {
        inject()
    }

    override fun onCleared() {
        disposables.clear()
    }

    private fun inject() {
        when (this) {
            is MainViewModel -> baseActivity.appComponent.inject(this)
            is DetailViewModel -> baseActivity.appComponent.inject(this)
        }
    }

}
