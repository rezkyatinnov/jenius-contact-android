package com.rezkyatinnov.jeniuscontact.ui.detail

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.afollestad.materialdialogs.MaterialDialog
import com.rezkyatinnov.jeniuscontact.R
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.restapi.ApiResponse
import com.rezkyatinnov.jeniuscontact.restapi.ErrorResponse
import com.rezkyatinnov.jeniuscontact.restapi.RestApi
import com.rezkyatinnov.jeniuscontact.restapi.RestSubscriber
import com.rezkyatinnov.jeniuscontact.ui.BaseViewModel
import com.rezkyatinnov.jeniuscontact.ui.createupdatecontact.CreateUpdateContactActivity
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
    var id = ""

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
        id = body.data!!.id?:""
    }

    override fun onFailed(error: ErrorResponse) {
    }

    val onUpdateClickListener = View.OnClickListener {
        val intent = Intent(activity,CreateUpdateContactActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("isUpdate",true)
        activity.startActivity(intent)
    }

    val onDeleteClickListener = View.OnClickListener {
        MaterialDialog(activity).show {
            message(null, "Are You sure you want to delete this contact?")
            cancelable(false)
            cancelOnTouchOutside(false)
            negativeButton(null,"CANCEL"){ dismiss() }
            positiveButton(null,"YES"){
                RestApi.call(
                    disposables,
                    apiServices.deleteContact(id),
                    object:RestSubscriber<ApiResponse<Void>>{
                        override fun onRestCallStart() {
                        }

                        override fun onRestCallFinish() {
                        }

                        override fun onSuccess(headers: Headers, body: ApiResponse<Void>?) {
                            MaterialDialog(activity).show {
                                message(null,body!!.message)
                                cancelable(false)
                                cancelOnTouchOutside(false)
                                positiveButton { activity.finish() }
                            }
                        }

                        override fun onFailed(error: ErrorResponse) {
                            MaterialDialog(activity).show {
                                message(null,error.message)
                                cancelable(false)
                                cancelOnTouchOutside(false)
                                positiveButton { dismiss() }
                            }
                        }

                    }
                )
            }
        }
    }
}
