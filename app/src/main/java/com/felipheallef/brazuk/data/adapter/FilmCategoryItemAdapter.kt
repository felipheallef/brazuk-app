package com.felipheallef.brazuk.data.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.data.model.FilmCategory


class FilmCategoryItemAdapter(private val context: Context, private val categories: List<FilmCategory>) :
    RecyclerView.Adapter<FilmCategoryItemAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvFilms: RecyclerView

        init {
            // Define click listener for the ViewHolder's View.
            rvFilms = view.findViewById(R.id.recycler_view_list)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_film_category, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
        viewHolder.rvFilms.setHasFixedSize(true)
        viewHolder.rvFilms.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewHolder.rvFilms.adapter = FilmItemAdapter(categories[position].films)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = categories.size

}
