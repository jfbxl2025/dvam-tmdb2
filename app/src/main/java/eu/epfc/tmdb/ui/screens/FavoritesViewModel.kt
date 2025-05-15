package eu.epfc.tmdb.ui.screens

import androidx.lifecycle.ViewModel
import eu.epfc.tmdb.data.MoviesRepository

class FavoritesViewModel( private val moviesRepository: MoviesRepository):ViewModel() {

    val movies
        get() = moviesRepository.getFavorites()


}