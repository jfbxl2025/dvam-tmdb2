package eu.epfc.tmdb

import android.app.Application
import android.content.Context
import eu.epfc.tmdb.data.AppContainer
import eu.epfc.tmdb.data.DefaultAppContainer
import eu.epfc.tmdb.data.services.AuthManager
import eu.epfc.tmdb.data.services.FavoritesManager
import eu.epfc.tmdb.network.TmdbService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class TmdbApplication: Application() {

    companion object {
        lateinit  var appContext: Context
    }

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        container = DefaultAppContainer

    }


}