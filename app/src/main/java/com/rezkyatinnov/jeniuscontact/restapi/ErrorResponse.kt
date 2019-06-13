package com.rezkyatinnov.jeniuscontact.restapi

import com.google.gson.annotations.SerializedName

/**
 * Created by rezkyatinnov on 08/08/2017.
 */

class ErrorResponse {
    @SerializedName("code")
    var code: Int = 0

    @SerializedName("message")
    var message: String? = null

    @SerializedName("description")
    var description: String? = null

    var httpStatus: HttpStatus? = null

    init {
        httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE
        this.code = httpStatus!!.value()
        this.message = httpStatus!!.reasonPhrase
        this.description = httpStatus!!.reasonPhrase
    }

    override fun toString(): String {
        return "ErrorResponse {" +
                "status='" + code + '\''.toString() +
                ", message='" + message + '\''.toString() +
                ", description='" + description + '\''.toString() +
                '}'.toString()
    }
}
