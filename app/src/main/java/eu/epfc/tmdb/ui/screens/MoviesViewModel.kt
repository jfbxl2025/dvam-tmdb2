package eu.epfc.tmdb.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.epfc.tmdb.data.MoviesRepository
import eu.epfc.tmdb.data.services.FavoritesManager
import eu.epfc.tmdb.data.model.Movie
import eu.epfc.tmdb.ui.Paginated
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesRepository: MoviesRepository,
    private val favoritesManager: FavoritesManager
): ViewModel(), Paginated  {

    override var page: Int by mutableIntStateOf(0)
    override var pagesCount: Int by mutableIntStateOf(12)
    var movies: List<Movie> by mutableStateOf(emptyList())

    init{
        fetchMovies(1)

        // Broadcasting receiver
        viewModelScope.launch {
            favoritesManager.updatedFavorite.collect { favorite ->
                movies.find { it.movieId == favorite.movieId }?.isFavorite = favorite.isFavorite
            }
        }
    }


    fun fetchNextMovies() {
        fetchMovies(page + 1 )
    }

    private fun fetchMovies(newPage: Int? = null) {
        newPage?.let { page = it }
        viewModelScope.launch {
            movies = movies + moviesRepository.getMovies(page).map {
                it.also { movie ->
                    movie.isFavorite =
                        favoritesManager.movies.find { movie.movieId == it.movieId }?.let { true } ?: false
                }
            }
        }
    }



    override fun getPrev() {
        fetchMovies(page - 1)
    }
    override fun getNext() {
        fetchMovies(page + 1)
    }



}