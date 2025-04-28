package eu.epfc.tmdb.data

import eu.epfc.tmdb.network.MoviesClient
import eu.epfc.tmdb.data.model.Movie
import eu.epfc.tmdb.data.model.Page

class MoviesRepository(private val client: MoviesClient) {



    suspend fun getMovies(page: Int): List<Movie> {
        return  client.getPopular(page = page).results

    }

    suspend fun getPopular(page: Int): Page<Movie> {
        return  client.getPopular(page = page)
    }





}