package com.felipheallef.brazuk.data.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.data.model.FilmCategory


class FilmCategoryItemAdapter(
    private val categories: List<FilmCategory>,
    private val manager: FragmentManager) :
    RecyclerView.Adapter<FilmCategoryItemAdapter.ViewHolder>() {

    lateinit var context: Context
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvFilms: RecyclerView = view.findViewById(R.id.recycler_view_list)
        val txtCategoryName: TextView = view.findViewById(R.id.txt_category_name)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_film_category, viewGroup, false)
        context = viewGroup.context
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.txtCategoryName.text = categories[position].title
        viewHolder.rvFilms.setHasFixedSize(true)
        viewHolder.rvFilms.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewHolder.rvFilms.adapter = FilmItemAdapter(categories[position].films, manager)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = categories.size

}
