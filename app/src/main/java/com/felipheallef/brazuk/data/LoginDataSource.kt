package com.felipheallef.brazuk.data

import android.util.Log
import com.felipheallef.brazuk.BuildConfig
import com.felipheallef.brazuk.api.ApiResponse
import com.felipheallef.brazuk.api.service.AccountService
import com.felipheallef.brazuk.data.model.User
import com.felipheallef.brazuk.util.NetworkUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val client = NetworkUtils.getRetrofitInstance(BuildConfig.BASE_URL)

//    fun login(username: String, password: String): User {

//        preparePostAsync(username, password) {
//            it
//        }

//        return result

//        try {
//
//
//
//            return result
//
////            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
//        } catch (e: Throwable) {
//            return Result.Error(IOException(e.message, e))
//        }
//    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun login(username: String, password: String, callback: (ApiResponse<User>) -> Unit) {
        val endpoint = client.create(AccountService::class.java)
        val request = endpoint.login(username, password)

        request.enqueue(object : Callback<ApiResponse<User>> {
            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
                Log.e("Retrofit", t.message, t)
            }

            override fun onResponse(
                    call: Call<ApiResponse<User>>,
                    response: Response<ApiResponse<User>>
            ) {
                Log.i("Retrofit", response.code().toString())

                if (response.isSuccessful) {
                    val result = response.body()!!
                    callback.invoke(result)
                    val output = Gson().toJson(result)
                    Log.i("Response", "\n\n$output\n\n")
                } else {
                    val body = response.errorBody()!!.string()
                    val type = object : TypeToken<ApiResponse<User>>() {}.type
                    val result: ApiResponse<User> = Gson().fromJson(body, type)
                    callback.invoke(result)
                    Log.d("Response", body)
                }
            }


        })

    }

}