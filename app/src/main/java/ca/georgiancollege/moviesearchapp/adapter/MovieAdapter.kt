package ca.georgiancollege.moviesearchapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ca.georgiancollege.moviesearchapp.databinding.ItemMovieBinding
import ca.georgiancollege.moviesearchapp.model.Movie
import ca.georgiancollege.moviesearchapp.ui.MovieDetailsActivity

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.apply {
            movieTitle.text = movie.Title
            movieDirector.text = "Director: ${movie.Director}"
            movieRating.text = "Rating: ${movie.imdbRating}"
            movieYear.text = "Year: ${movie.Year}"
            Glide.with(moviePoster.context).load(movie.Poster).into(moviePoster)

            root.setOnClickListener {
                val intent = Intent(root.context, MovieDetailsActivity::class.java)
                intent.putExtra("TITLE", movie.Title)
                intent.putExtra("YEAR", movie.Year)
                intent.putExtra("DIRECTOR", movie.Director)
                intent.putExtra("RATING", movie.imdbRating)
                intent.putExtra("PLOT", movie.Plot)
                intent.putExtra("POSTER", movie.Poster)
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = movies.size
}