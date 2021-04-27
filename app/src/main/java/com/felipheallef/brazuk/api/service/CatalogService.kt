package com.felipheallef.brazuk.api.service

import com.felipheallef.brazuk.data.model.Film
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class FilmResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<Film>
)

interface CatalogService {

    @GET("/catalog/films")
    fun getAllFilms() : Call<FilmResponse>

}