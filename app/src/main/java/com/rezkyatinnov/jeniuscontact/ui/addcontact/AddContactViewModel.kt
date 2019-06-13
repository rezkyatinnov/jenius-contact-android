package com.rezkyatinnov.jeniuscontact.ui.addcontact

import android.text.Editable
import android.text.TextWatcher
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
import okhttp3.Headers

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class AddContactViewModel(var activity: AddContactActivity):BaseViewModel(activity), RestSubscriber<ApiResponse<Void>> {

    var avatar = MutableLiveData<String>()
    var firstname = ""
    var lastname = ""
    var age = ""

    val avatarTextChangeListener = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            avatar.value = s.toString()
        }
    }

    val firstnameTextChangeListener = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            firstname = s.toString()
        }
    }

    val lastnameTextChangeListener = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            lastname = s.toString()
        }
    }

    val ageTextChangeListener = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            age = s.toString()
        }
    }

    val onSaveClickListener = View.OnClickListener {
        if (isFormValid()) {
            saveContact()
        } else {
            MaterialDialog(activity).show {
                message(R.string.fill_all_fields)
                positiveButton {
                    dismiss()
                }
            }
        }
    }

    fun saveContact(){
        val contact = Contact()
        contact.firstName = firstname
        contact.lastName = lastname
        contact.age = age.toInt()
        contact.photo = avatar.value

        RestApi.call(
            disposables,
            apiServices.postContact(contact),
            this
        )
    }

    fun isFormValid(): Boolean{
        return !avatar.value.isNullOrBlank() && !firstname.isNullOrBlank() && !lastname.isNullOrBlank() && !age.isNullOrBlank()
    }

    override fun onRestCallStart() {
    }

    override fun onRestCallFinish() {
    }

    override fun onSuccess(headers: Headers, body: ApiResponse<Void>?) {
        MaterialDialog(activity).show {
            message(null,body!!.message)
            positiveButton {
                activity.finish()
            }
            cancelOnTouchOutside(false)
            cancelable(false)
        }
    }

    override fun onFailed(error: ErrorResponse) {
        MaterialDialog(activity).show {
            message(null,error.message)
            positiveButton {
                dismiss()
            }
        }
    }
}
