package com.rezkyatinnov.jeniuscontact.restapi

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

object RestApi {
     fun <T> call(disposable: CompositeDisposable, observable: Observable<Response<T>>, restSubscriber: RestSubscriber<T>){
         disposable.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { restSubscriber.onRestCallStart() }
            .doOnTerminate { restSubscriber.onRestCallFinish() }
            .subscribe(
                { result -> restSubscriber.onRestCallSuccess(result) },
                { throwable -> restSubscriber.onRestCallError(throwable) }
            )
        )
    }
}
