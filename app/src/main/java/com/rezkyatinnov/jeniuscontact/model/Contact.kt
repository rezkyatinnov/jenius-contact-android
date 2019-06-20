package com.rezkyatinnov.jeniuscontact.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rezkyatinnov on 13/06/2019.
 */


class Contact {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("lastName")
    @Expose
    var lastName: String? = null
    @SerializedName("age")
    @Expose
    var age: Int? = null
    @SerializedName("photo")
    @Expose
    var photo: String? = null
}
