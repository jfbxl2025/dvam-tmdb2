package eu.epfc.tmdb.data.services

import android.util.Log
import eu.epfc.tmdb.data.model.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import eu.epfc.tmdb.data.model.Movie
import eu.epfc.tmdb.data.repositories.FavoritesRepository

class FavoritesManager (private val repository: FavoritesRepository) {

    private val _updatedFavorite = MutableSharedFlow<Favorite>(replay = 0)
    val updatedFavorite: SharedFlow<Favorite> = _updatedFavorite

    private val _movies: MutableList<Movie> = emptyList<Movie>().toMutableList()
    val movies: List<Movie> = _movies

    init {
        val context: CoroutineContext = Job() + Dispatchers.IO
        val scope = CoroutineScope(context)
        scope.launch {
            repository.getFavorites().forEach { _movies.add(it) }
        }
    }


    suspend fun setFavorite(movie: Movie, isFavorite: Boolean): Boolean {

        val favorite = Favorite(movieId = movie.movieId, isFavorite = isFavorite)

        return if (saveFavorite(favorite)) {
            movie.isFavorite = isFavorite
            if(isFavorite) _movies.add(movie)
            else _movies.find { it.movieId == movie.movieId }?.let { _movies.remove(it) }

            _updatedFavorite.emit(favorite)
            true

        } else false
    }

    private suspend fun saveFavorite(favorite: Favorite): Boolean {
        return try {
            repository.saveFavorite(favorite)
            true
        }
        catch (e: Exception) {
            Log.e("Fav Manager", "can't saveFavorite: ${e.message?: "???"}")
            false
        }

    }

}