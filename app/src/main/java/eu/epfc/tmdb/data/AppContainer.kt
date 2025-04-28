package eu.epfc.tmdb.data

import eu.epfc.tmdb.TmdbApplication
import eu.epfc.tmdb.data.repositories.DetailsRepository
import eu.epfc.tmdb.data.repositories.FavoritesRepository
import eu.epfc.tmdb.data.services.AuthManager
import eu.epfc.tmdb.data.services.FavoritesManager
import eu.epfc.tmdb.network.TmdbService

interface AppContainer {
    val moviesRepository: MoviesRepository
    val detailsRepository: DetailsRepository
    val favoritesRepository: FavoritesRepository
    val authManager: AuthManager
    val favoritesManager: FavoritesManager
}

object DefaultAppContainer : AppContainer {


    override val moviesRepository: MoviesRepository by lazy {
        MoviesRepository(TmdbService.moviesClient)
    }
    override val detailsRepository: DetailsRepository by lazy {
        DetailsRepository(TmdbService.moviesClient)
    }
    override val favoritesRepository: FavoritesRepository by lazy {
        FavoritesRepository(TmdbService.moviesClient)
    }
    override val authManager: AuthManager by lazy {
        AuthManager(TmdbApplication.appContext,TmdbService.authClient, TmdbService.setSessionId)
    }
    override val favoritesManager: FavoritesManager by lazy {
        FavoritesManager(favoritesRepository)
    }


}

