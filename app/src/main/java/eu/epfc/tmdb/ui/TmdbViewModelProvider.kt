package eu.epfc.tmdb.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import eu.epfc.tmdb.TmdbApplication
import eu.epfc.tmdb.ui.screens.FavoritesViewModel
import eu.epfc.tmdb.ui.screens.DetailsViewModel
import eu.epfc.tmdb.ui.screens.HomeViewModel
import eu.epfc.tmdb.ui.screens.MoviesViewModel

object TmdbViewModelProvider {

        val Factory = viewModelFactory {

            initializer {
                HomeViewModel( tmdbApplication().container.authManager )
            }

            initializer {
                FavoritesViewModel(tmdbApplication().container.moviesRepository)
            }
            initializer {
                MoviesViewModel(tmdbApplication().container.moviesRepository)
            }
            initializer {
                DetailsViewModel(this.createSavedStateHandle(),tmdbApplication().container.moviesRepository)
            }

    }

    private fun CreationExtras.tmdbApplication(): TmdbApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TmdbApplication)
}