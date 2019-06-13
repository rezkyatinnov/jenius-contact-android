package com.rezkyatinnov.jeniuscontact.restapi

import com.rezkyatinnov.jeniuscontact.model.Contact
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

interface ApiServices {
    @GET(ApiUrl.CONTACT)
    fun getContacts(): Observable<Response<ApiResponse<ArrayList<Contact>>>>
}