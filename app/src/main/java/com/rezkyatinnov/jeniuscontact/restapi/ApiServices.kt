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

    @GET(ApiUrl.CONTACT_BY_ID)
    fun getContact(@Path("id") id:String): Observable<Response<ApiResponse<Contact>>>

    @POST(ApiUrl.CONTACT)
    fun postContact(@Body contact: Contact): Observable<Response<ApiResponse<Void>>>

    @PUT(ApiUrl.CONTACT_BY_ID)
    fun putContact(@Path("id") id:String, @Body contact: Contact): Observable<Response<ApiResponse<Contact>>>

    @DELETE(ApiUrl.CONTACT_BY_ID)
    fun deleteContact(@Path("id") id:String): Observable<Response<ApiResponse<Void>>>
}