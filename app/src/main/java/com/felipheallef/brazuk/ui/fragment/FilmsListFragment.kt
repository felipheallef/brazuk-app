package com.felipheallef.brazuk.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.felipheallef.brazuk.BuildConfig
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.api.service.CatalogService
import com.felipheallef.brazuk.api.service.FilmResponse
import com.felipheallef.brazuk.data.adapter.FilmCategoryItemAdapter
import com.felipheallef.brazuk.data.adapter.FilmItemAdapter
import com.felipheallef.brazuk.data.model.Film
import com.felipheallef.brazuk.data.model.FilmCategory
import com.felipheallef.brazuk.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListFragment : Fragment() {

    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    lateinit var filmList: List<Film>
    lateinit var rvFilms: RecyclerView

    var filmsCategory: MutableList<FilmCategory> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_films_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shimmerFrameLayout = activity?.findViewById(R.id.shimmer_view_container)!!
        rvFilms = activity?.findViewById(R.id.rv_films)!!

        getData()

    }

    private fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance(BuildConfig.BASE_URL)

        val endpoint = retrofitClient.create(CatalogService::class.java)
        val callback = endpoint.getAllFilms()

        shimmerFrameLayout.startShimmer()

        callback.enqueue(object : Callback<FilmResponse> {
            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                Log.i("Retrofit", response.body()?.status.toString())

                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE

                filmList = response.body()?.data!!
                filmsCategory.add(FilmCategory("Seus filmes", filmList))

                rvFilms.setHasFixedSize(true)
                rvFilms.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                rvFilms.adapter = FilmItemAdapter(filmList, fragmentManager!!)
                rvFilms.adapter = FilmCategoryItemAdapter(filmsCategory, fragmentManager!!)
            }
        })

    }

}