package com.rezkyatinnov.jeniuscontact.ui.createupdatecontact

import android.app.Activity
import android.content.Intent
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

open class CreateUpdateContactViewModel(var activity: CreateUpdateContactActivity) : BaseViewModel(activity),
    RestSubscriber<ApiResponse<Void>> {

    var loadingVisibility = MutableLiveData<Int>()

    var avatar = MutableLiveData<String>()
    var firstname = ""
    var lastname = ""
    var age = ""
    var avatarMutable = MutableLiveData<String>()
    var firstnameMutable = MutableLiveData<String>()
    var lastnameMutable = MutableLiveData<String>()
    var ageMutable = MutableLiveData<String>()
    var contact = Contact()

    init {
        loadingVisibility.value = View.GONE
    }

    val avatarTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            avatar.value = s.toString()
        }
    }

    val firstnameTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            firstname = s.toString()
        }
    }

    val lastnameTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            lastname = s.toString()
        }
    }

    val ageTextChangeListener = object : TextWatcher {
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
            contact.firstName = firstname
            contact.lastName = lastname
            contact.age = age.toInt()
            contact.photo = avatar.value
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

    var preloadRestSubscriber = object : RestSubscriber<ApiResponse<Contact>> {
        override fun onRestCallStart() {
            loadingVisibility.value = View.VISIBLE
        }

        override fun onRestCallFinish() {
            loadingVisibility.value = View.GONE
        }

        override fun onSuccess(headers: Headers, body: ApiResponse<Contact>?) {
            loadingVisibility.value = View.GONE
            avatarMutable.value = body!!.data!!.photo
            avatar.value = body.data!!.photo
            firstnameMutable.value = body.data!!.firstName
            lastnameMutable.value = body.data!!.lastName
            ageMutable.value = body.data!!.age.toString()
        }

        override fun onFailed(error: ErrorResponse) {
            loadingVisibility.value = View.GONE
            MaterialDialog(activity).show {
                message(null, error.message)
                positiveButton {
                    dismiss()
                    activity.finish()
                }
            }
        }

    }

    fun preloadContactData(id: String) {
        RestApi.call(
            disposables,
            apiServices.getContact(id),
            preloadRestSubscriber,
            schedulerProvider
        )
    }

    var updateRestSubscriber = object : RestSubscriber<ApiResponse<Contact>> {
        override fun onRestCallStart() {
            loadingVisibility.value = View.VISIBLE
        }

        override fun onRestCallFinish() {
            loadingVisibility.value = View.GONE
        }

        override fun onSuccess(headers: Headers, body: ApiResponse<Contact>?) {
            loadingVisibility.value = View.GONE
            MaterialDialog(activity).show {
                message(null, body!!.message)
                positiveButton {
                    val resultIntent = Intent()
                    activity.setResult(Activity.RESULT_OK, resultIntent)
                    activity.finish()
                }
                cancelOnTouchOutside(false)
                cancelable(false)
            }
        }

        override fun onFailed(error: ErrorResponse) {
            loadingVisibility.value = View.GONE
            MaterialDialog(activity).show {
                message(null, error.message)
                positiveButton {
                    dismiss()
                }
            }
        }

    }

    fun saveContact() {
        if (activity.isUpdate) {
            RestApi.call(
                disposables,
                apiServices.putContact(activity.id, contact),
                updateRestSubscriber,
                schedulerProvider
            )
        } else {
            RestApi.call(
                disposables,
                apiServices.postContact(contact),
                this,
                schedulerProvider
            )
        }
    }

    fun isFormValid(): Boolean {
        return !avatar.value.isNullOrBlank() && !firstname.isBlank() && !lastname.isBlank() && !age.isBlank()
    }

    override fun onRestCallStart() {
        loadingVisibility.value = View.VISIBLE
    }

    override fun onRestCallFinish() {
        loadingVisibility.value = View.GONE
    }

    override fun onSuccess(headers: Headers, body: ApiResponse<Void>?) {
        loadingVisibility.value = View.GONE
        MaterialDialog(activity).show {
            message(null, body!!.message)
            positiveButton {
                val resultIntent = Intent()
                activity.setResult(Activity.RESULT_OK, resultIntent)
                activity.finish()
            }
            cancelOnTouchOutside(false)
            cancelable(false)
        }
    }

    override fun onFailed(error: ErrorResponse) {
        loadingVisibility.value = View.GONE
        MaterialDialog(activity).show {
            message(null, error.message)
            positiveButton {
                dismiss()
            }
        }
    }
}
