package com.rezkyatinnov.jeniuscontact.restapi

import com.google.gson.Gson
import okhttp3.Headers
import retrofit2.Response

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


interface RestSubscriber<T> {
    fun onRestCallStart()
    fun onRestCallFinish()
    fun onRestCallSuccess(result:Response<T>){
        if (result.isSuccessful) {
            onSuccess(result.headers(), result.body())
        } else {
            val gson = Gson()
            try {
                val error = gson.fromJson(result.errorBody()!!.string(), ErrorResponse::class.java)
                onFailed(error)
            }catch (throwable: Throwable) {
                onRestCallError(throwable)
            }
        }
    }
    fun onRestCallError(throwable: Throwable){
        val errorRes = ErrorResponse()
        errorRes.message = throwable.message
        errorRes.code = throwable.hashCode()
        onFailed(errorRes)
    }

    fun onSuccess(headers: Headers, body: T?)
    fun onFailed(error: ErrorResponse)
}
