package com.felipheallef.brazuk.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.data.model.Film
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilmBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var film: Film
    lateinit var ivFilmCover: ImageView
    lateinit var tvFilmTitle: TextView
    lateinit var tvFilmDescription: TextView

    override fun getTheme(): Int {
        return R.style.Theme_Brazuk_BottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottomsheet_film_details, container, false)
        ivFilmCover = view.findViewById(R.id.iv_film_cover)
        tvFilmTitle = view.findViewById(R.id.tv_film_title)
        tvFilmDescription = view.findViewById(R.id.tv_film_description)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFilmInfo()
    }

    private fun setupFilmInfo() {
        tvFilmTitle.text = film.title
        tvFilmDescription.text = film.description

        Glide.with(context!!)
                .load("https://coprocen-api.herokuapp.com/img/films/covers/${film.slug}.jpg")
                .centerCrop()
                .into(ivFilmCover)

    }

    companion object {

        fun getInstance(film: Film): FilmBottomSheetFragment {
            val fragment = FilmBottomSheetFragment()
            fragment.film = film
            return fragment
        }

    }
}