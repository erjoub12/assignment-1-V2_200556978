package ca.georgiancollege.moviesearchapp.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.georgiancollege.moviesearchapp.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun searchMovies(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val urlString = "http://www.omdbapi.com/?s=$searchQuery&apikey=47f367f4"
            val url = URL(urlString)

            val connection = url.openConnection() as HttpURLConnection
            try {
                val data = connection.inputStream.bufferedReader().readText()
                val json = JSONObject(data)

                val searchResults = json.optJSONArray("Search")
                val movieList = mutableListOf<Movie>()

                for (i in 0 until (searchResults?.length() ?: 0)) {
                    val item = searchResults!!.getJSONObject(i)
                    val movieDetails = getMovieDetails(item.getString("imdbID"))
                    movieList.add(movieDetails)
                }

                _movies.postValue(movieList)

            } finally {
                connection.disconnect()
            }
        }
    }

    private fun getMovieDetails(imdbID: String): Movie {
        val detailsUrl = URL("http://www.omdbapi.com/?i=$imdbID&apikey=47f367f4")
        val connection = detailsUrl.openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        connection.disconnect()

        val json = JSONObject(data)
        return Movie(
            Title = json.optString("Title"),
            Year = json.optString("Year"),
            Director = json.optString("Director"),
            imdbRating = json.optString("imdbRating"),
            Plot = json.optString("Plot"),
            Poster = json.optString("Poster")
        )
    }
}