package com.felipheallef.brazuk.api.service

import com.felipheallef.brazuk.api.ApiResponse
import com.felipheallef.brazuk.data.model.User
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AccountService {

    @FormUrlEncoded
    @POST("/account/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String): Call<ApiResponse<User>>

}