package eu.epfc.tmdb.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import eu.epfc.tmdb.data.model.Details
import eu.epfc.tmdb.data.model.Favorite
import eu.epfc.tmdb.network.MoviesClient
import eu.epfc.tmdb.data.model.Movie
import eu.epfc.tmdb.data.model.Page
import eu.epfc.tmdb.data.model.Review
//import eu.epfc.tmdb.data.services.FavoritesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/*
    Very Important:
    A movie from the favorites list may not be present in the movie list (because it has not yet been loaded)
 */
class MoviesRepository( private val client: MoviesClient ) {

    private var page: Int = 0
    val movies: MutableList<Movie> = mutableListOf()
    private val _favorites: MutableList<Movie> = mutableListOf()

    init {
        val context: CoroutineContext = Job() + Dispatchers.IO
        val scope = CoroutineScope(context)

        scope.launch {
            _favorites.addAll(fetchAllFavorites())
        }
    }

    fun getFavorites(): List<Movie> = _favorites.filter { it.isFavorite }


    suspend fun fetchMore() {
        page += 1
        movies.addAll(client.getPopular(page = page).results.map {
            it.also { movie ->
                movie.isFavorite = _favorites.find { it.movieId == movie.movieId } ?.let { it.isFavorite} ?: false
            }
        }
        )
    }

    private suspend fun fetchAllFavorites(): List<Movie> {
        val movies = mutableListOf<Movie>()
        try {

            val firstPage = client.getFavorites(1)
            firstPage.results.forEach { movies.add(it.also { it.isFavorite = true } )}
            for (i in 2..firstPage.totalPages ) {
                client.getFavorites(page = i).results.forEach { movies.add(it.also { it.isFavorite = true }) }
            }
        } catch (e: Exception) {
            Log.e("REPO", "can't fetch favorites. ${e.message ?: "unknown error"}")
        }
        return movies
    }

    suspend fun setFavorite(movieId: Int, isFavorite: Boolean): Boolean {
        val favorite = Favorite(movieId = movieId, isFavorite = isFavorite)

        return if (saveFavorite(favorite)) {
            if (isFavorite) {
                movies.find { it.movieId == movieId }?.let {
                    it.isFavorite = true
                    _favorites.add(it)
                }
            } else {
                movies.find { it.movieId == movieId }?.let {
                    it.isFavorite = false
                }
                _favorites.find { it.movieId == movieId }?.let {
                    _favorites.remove(it)
                }
            }
            true
        } else  {
            false

        }
    }

    private suspend fun saveFavorite(favorite: Favorite): Boolean {
        return try {
            val result = client.postFavorite(favorite)
            if (result.success != true) {
                Log.e("FAV repo", "Can't save favorites: $result")
            }
            return result.success ?: false
        }
        catch (e: Exception) {
            Log.e("FAV repo", "Can't save favorites: ${e.message?:"unknown"}")
            false
        }

    }

    suspend fun getDetails(movieId: Int): Details {
        return client.getDetails(movieId).also {it.isFavorite = _favorites.find{ it.movieId == movieId }?.let{true} ?: false }
    }

    suspend fun getReviews(movieId: Int): List<Review> {
        return client.getReviews(movieId).results
    }

}