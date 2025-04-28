package eu.epfc.tmdb.data.repositories

import android.util.Log
import eu.epfc.tmdb.network.MoviesClient
import eu.epfc.tmdb.data.model.Favorite
import eu.epfc.tmdb.data.model.Movie
import eu.epfc.tmdb.data.model.Page


class FavoritesRepository(
    private val client: MoviesClient
){

    suspend fun getFavorites(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        val firstPage = client.getFavorites(1)
        firstPage.results.forEach { movies.add(it.copy(isFavorite = true)) }
        for (i in 2..firstPage.totalPages ) {
            client.getFavorites(page = i).results.forEach { movies.add(it.copy(isFavorite = true)) }
        }
        return  movies
    }

    suspend fun saveFavorite(favorite: Favorite): Boolean {
        return try {
            val result = client.setFavorite(favorite)
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


}