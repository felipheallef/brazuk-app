package com.felipheallef.brazuk.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<T: Any> (
        @SerializedName("status")
        val status: Int,
        @SerializedName("success")
        val success: Boolean,
        @SerializedName("error")
        val error: ApiError? = null,
        @SerializedName("data")
        val data: T? = null
        )