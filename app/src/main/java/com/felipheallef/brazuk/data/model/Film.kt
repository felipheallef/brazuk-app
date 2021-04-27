package com.felipheallef.brazuk.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Film (
    @SerializedName("film_id")
    var filmId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("release_year")
    val releaseYear: Int,

    @SerializedName("language_id")
    val languageId: Int,

    @SerializedName("original_language_id")
    val originalLanguageId: Int,

    @SerializedName("length")
    val length: Int,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("time_added")
    val timeAdded: String
)