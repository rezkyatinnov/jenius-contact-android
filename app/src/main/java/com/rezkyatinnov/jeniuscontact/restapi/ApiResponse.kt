package com.rezkyatinnov.jeniuscontact.restapi

import com.google.gson.annotations.SerializedName

/**
 * Created by rezkyatinnov on 13/06/2019.
 */

class ApiResponse<T> {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: T? = null
}