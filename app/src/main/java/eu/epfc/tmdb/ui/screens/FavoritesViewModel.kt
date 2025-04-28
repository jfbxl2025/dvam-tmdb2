package eu.epfc.tmdb.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.epfc.tmdb.data.model.Movie
import eu.epfc.tmdb.data.services.FavoritesManager
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val manager: FavoritesManager
):ViewModel() {

    var movies: List<Movie> by mutableStateOf(manager.movies)

    init{
        // Broadcasting receiver
        viewModelScope.launch {
            manager.updatedFavorite.collect { favorite ->
                if(!favorite.isFavorite) movies = manager.movies
            }
        }
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            manager.setFavorite(movie = movie, isFavorite = false)
        }
    }
}