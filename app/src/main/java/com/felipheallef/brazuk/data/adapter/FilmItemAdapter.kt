package com.felipheallef.brazuk.data.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felipheallef.brazuk.BuildConfig
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.data.model.Film
import com.felipheallef.brazuk.ui.fragment.FilmBottomSheetFragment

class FilmItemAdapter(
    private val films: List<Film>,
    private val manager: FragmentManager) :
    RecyclerView.Adapter<FilmItemAdapter.ViewHolder>() {

    lateinit var context: Context

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_film_title)
        val ivFilmCover: ImageView = view.findViewById(R.id.iv_film_cover)

        init {
            // Define click listener for the ViewHolder's View.

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_film, viewGroup, false)

        context = viewGroup.context

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = films[position].title
        val slug = films[position].slug

        viewHolder.ivFilmCover.setOnClickListener {
            FilmBottomSheetFragment.getInstance(films[position]).apply {
                this.show(manager, tag)
            }
        }

        Glide
            .with(context)
            .load(BuildConfig.BASE_URL + "img/films/covers/$slug.jpg")
            .centerCrop()
//            .placeholder(R.drawable.loading_spinner)
            .into(viewHolder.ivFilmCover)

    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount() = films.size

}
