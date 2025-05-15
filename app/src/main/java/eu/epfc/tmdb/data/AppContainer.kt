package eu.epfc.tmdb.data

import eu.epfc.tmdb.TmdbApplication
import eu.epfc.tmdb.data.services.AuthManager
import eu.epfc.tmdb.network.TmdbService

interface AppContainer {
    val moviesRepository: MoviesRepository
    val authManager: AuthManager
}

object DefaultAppContainer : AppContainer {

    override val moviesRepository: MoviesRepository by lazy {
        MoviesRepository(TmdbService.moviesClient)
    }
    override val authManager: AuthManager by lazy {
        AuthManager(TmdbApplication.appContext,TmdbService.authClient, TmdbService.setSessionId)
    }

}

