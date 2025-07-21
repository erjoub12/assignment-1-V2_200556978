package ca.georgiancollege.moviesearchapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.moviesearchapp.adapter.MovieAdapter
import ca.georgiancollege.moviesearchapp.databinding.ActivityMainBinding
import ca.georgiancollege.moviesearchapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchButton.setOnClickListener {
            val query = binding.searchField.text.toString()
            if (query.isNotBlank()) {
                viewModel.searchMovies(query)
            }
        }

        viewModel.movies.observe(this) { movies ->
            binding.recyclerView.adapter = MovieAdapter(movies)
        }
    }
}