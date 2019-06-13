package com.rezkyatinnov.jeniuscontact.ui.detail

import androidx.lifecycle.MutableLiveData
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.restapi.ApiResponse
import com.rezkyatinnov.jeniuscontact.restapi.ErrorResponse
import com.rezkyatinnov.jeniuscontact.restapi.RestApi
import com.rezkyatinnov.jeniuscontact.restapi.RestSubscriber
import com.rezkyatinnov.jeniuscontact.ui.BaseViewModel
import okhttp3.Headers

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class DetailViewModel(var activity: DetailActivity):BaseViewModel(activity),
    RestSubscriber<ApiResponse<Contact>> {

    val firstname = MutableLiveData<String>()
    val lastname = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val avatar = MutableLiveData<String>()

    fun loadContactDetail(id:String){
        RestApi.call(
            disposables,
            apiServices.getContact(id),
            this
        )
    }

    override fun onRestCallStart() {
    }

    override fun onRestCallFinish() {
    }

    override fun onSuccess(headers: Headers, body: ApiResponse<Contact>?) {
        avatar.value = body!!.data!!.photo
        firstname.value = body.data!!.firstName
        lastname.value = body.data!!.lastName
        age.value = (body.data!!.age?:0).toString() + " yo"
    }

    override fun onFailed(error: ErrorResponse) {
    }
}
