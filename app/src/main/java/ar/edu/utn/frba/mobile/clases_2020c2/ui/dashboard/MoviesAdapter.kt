package ar.edu.utn.frba.mobile.clases_2020c2.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.utn.frba.mobile.clases_2020c2.R
import ar.edu.utn.frba.mobile.clases_2020c2.core.Movie
import kotlinx.android.synthetic.main.view_listitem_movie.view.*

class MoviesAdapter(private val myDataset: MutableList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private val VIEWTYPE_CATEGORY: Int = 1
    private val VIEWTYPE_MOVIE: Int = 2

    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return if(myDataset[position].IsCategory)
            VIEWTYPE_CATEGORY
        else
            VIEWTYPE_MOVIE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.MyViewHolder {
        val view : View = if(viewType == VIEWTYPE_CATEGORY)
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_listitem_category, parent, false)
        else
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_listitem_movie, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindMovie(myDataset[position])
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val viewMovieName: TextView = itemView.movie_name
        private val viewMoviePoster: ImageView? = itemView.movie_poster

        fun bindMovie(movie: Movie) {
            viewMovieName.text = movie.movieName
            if(movie.moviePoster != null)
                viewMoviePoster!!.setImageResource(movie.moviePoster)
        }
    }
}