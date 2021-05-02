package com.felipheallef.brazuk.data

import android.util.Log
import com.felipheallef.brazuk.BuildConfig
import com.felipheallef.brazuk.api.service.AccountService
import com.felipheallef.brazuk.util.NetworkUtils
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val client = NetworkUtils.getRetrofitInstance(BuildConfig.BASE_URL)


    constructor() {

    }

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

    fun login(username: String, password: String, callback: (AccountService.Response) -> Unit) {
        val endpoint = client.create(AccountService::class.java)
        val request = endpoint.login(username, password)

        request.enqueue(object : Callback<AccountService.Response> {
            override fun onFailure(call: Call<AccountService.Response>, t: Throwable) {
                Log.e("Retrofit", t.message, t)
            }

            override fun onResponse(
                call: Call<AccountService.Response>,
                response: Response<AccountService.Response>
            ) {
                Log.i("Retrofit", response.code().toString())

                if (response.code() == 200) {
                    val result = response.body()!!
                    callback.invoke(result)
                }
            }
        })
    }

}