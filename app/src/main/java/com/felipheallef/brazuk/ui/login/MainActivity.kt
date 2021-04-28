package com.felipheallef.brazuk.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.api.service.CatalogService
import com.felipheallef.brazuk.api.service.FilmResponse
import com.felipheallef.brazuk.data.adapter.FilmItemAdapter
import com.felipheallef.brazuk.data.model.Film
import com.felipheallef.brazuk.util.NetworkUtils
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}