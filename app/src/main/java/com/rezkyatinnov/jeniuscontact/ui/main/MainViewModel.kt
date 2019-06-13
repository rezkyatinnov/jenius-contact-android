package com.rezkyatinnov.jeniuscontact.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

class MainViewModel(var activity: MainActivity) : BaseViewModel(activity),
    RestSubscriber<ApiResponse<ArrayList<Contact>>> {

    val mainListAdapter = MainListAdapter(activity, ArrayList())
    val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    val isRefresh = MutableLiveData<Boolean>()
    val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadAllContact()
    }

    init {
        loadAllContact()
    }

    fun loadAllContact() {
        RestApi.call(
            disposables,
            apiServices.getContacts(),
            this
        )
    }

    override fun onRestCallStart() {
        isRefresh.value = true
    }

    override fun onRestCallFinish() {
        isRefresh.value = false
    }

    override fun onSuccess(headers: Headers, body: ApiResponse<ArrayList<Contact>>?) {
        isRefresh.value = false
        mainListAdapter.updateData(body!!.data!!)
    }

    override fun onFailed(error: ErrorResponse) {
        isRefresh.value = false
    }
}
