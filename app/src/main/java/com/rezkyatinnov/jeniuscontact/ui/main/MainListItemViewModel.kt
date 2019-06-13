package com.rezkyatinnov.jeniuscontact.ui.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.ui.BaseViewModel
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailActivity

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class MainListItemViewModel(var activity: MainActivity):BaseViewModel(activity){

    val name = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val avatar = MutableLiveData<String>()
    lateinit var contact: Contact

    fun bind(contact: Contact) {
        this.contact = contact
        name.value = contact.firstName + " " + contact.lastName
        age.value = contact.age.toString() + " yo"
        avatar.value = contact.photo
    }

    var onItemClickListener = View.OnClickListener {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id", contact.id)
        activity.startActivity(intent)
    }

}
