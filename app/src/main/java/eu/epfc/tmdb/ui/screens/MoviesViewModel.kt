package eu.epfc.tmdb.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.epfc.tmdb.data.MoviesRepository
import eu.epfc.tmdb.data.model.Movie
import kotlinx.coroutines.launch

class MoviesViewModel( private val moviesRepository: MoviesRepository ): ViewModel() {

    var movies: List<Movie> by mutableStateOf(emptyList())

    init{
        fetchMore()
    }

    fun fetchMore() {
        viewModelScope.launch {
            moviesRepository.fetchMore()
            movies = moviesRepository.movies
        }
    }
}