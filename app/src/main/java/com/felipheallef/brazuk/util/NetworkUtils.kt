package com.felipheallef.brazuk.util

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class NetworkUtils {

    companion object {

        var client = OkHttpClient()

        /** Returns a Retrofit Client Instance for requisitions
         * @param path API Endpoint
         */
        fun getRetrofitInstance(path: String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Throws(IOException::class)
        fun getFromUrl(url: String?): String? {
            val request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).execute()
                .use { response -> return response.body()?.string() }
        }

    }

}