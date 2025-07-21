package ca.georgiancollege.moviesearchapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ca.georgiancollege.moviesearchapp.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("TITLE")
        val year = intent.getStringExtra("YEAR")
        val director = intent.getStringExtra("DIRECTOR")
        val rating = intent.getStringExtra("RATING")
        val plot = intent.getStringExtra("PLOT")
        val poster = intent.getStringExtra("POSTER")

        binding.detailsTitle.text = title
        binding.detailsYear.text = "Year: $year"
        binding.detailsDirector.text = "Director: $director"
        binding.detailsRating.text = "Rating: $rating"
        binding.detailsPlot.text = "Plot: $plot"
        Glide.with(this).load(poster).into(binding.detailsPoster)

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}