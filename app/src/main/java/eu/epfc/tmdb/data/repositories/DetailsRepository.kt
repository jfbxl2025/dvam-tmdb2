package eu.epfc.tmdb.data.repositories

import eu.epfc.tmdb.data.model.Details
import eu.epfc.tmdb.network.MoviesClient



class DetailsRepository(private val client: MoviesClient) {

    suspend fun getDetails(movieId: Int) : Details {
        return client.getDetails(movieId)
    }


}