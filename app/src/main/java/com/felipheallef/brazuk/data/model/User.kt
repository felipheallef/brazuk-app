package com.felipheallef.brazuk.data.model

import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
        ) {
    val displayName: String
    get() = "$name $surname"
}
