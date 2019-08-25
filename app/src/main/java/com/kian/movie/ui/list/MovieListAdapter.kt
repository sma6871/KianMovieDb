package com.kian.movie.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kian.movie.R
import com.kian.movie.data.models.MovieItem
import com.kian.movie.extensions.inflate
import kotlinx.android.synthetic.main.list_item.view.*

class MovieListAdapter(private var movies: List<MovieItem>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item, parent))
    }

    override fun getItemCount() = movies.size

    /**
     * call this method to update movies list
     **/
    fun updateData(movies: List<MovieItem>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        movies.getOrNull(position)?.let {
            holder.txtRate.text = it.voteAverage.toString()
            holder.txtTitle.text = it.title.toString()
            holder.txtYear.text = it.releaseDate.toString()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtYear = itemView.txtYear
        val txtTitle = itemView.txtTitle
        val txtRate = itemView.txtRate
    }
}