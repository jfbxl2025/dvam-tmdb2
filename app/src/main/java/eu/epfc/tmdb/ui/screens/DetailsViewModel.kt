package eu.epfc.tmdb.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import eu.epfc.tmdb.data.model.Details
import eu.epfc.tmdb.data.model.toMovie
import eu.epfc.tmdb.data.repositories.DetailsRepository
import eu.epfc.tmdb.data.services.FavoritesManager
import eu.epfc.tmdb.ui.DetailsDestination
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val detailsRepository: DetailsRepository,
    private val favoritesManager: FavoritesManager
) : ViewModel() {

    private val movieId = savedStateHandle.toRoute<DetailsDestination>().movieId


    var details: Details by mutableStateOf(Details())
    var isFavorite: Boolean by mutableStateOf( favoritesManager.movies.find { it.movieId == movieId }?.let { true } ?: false)

    init {
        viewModelScope.launch {
            try {
                details = detailsRepository.getDetails(movieId)
            }
            catch (e:Exception) {
                Log.e("detail VM",e.message ?: "error")
            }
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            if (favoritesManager.setFavorite(details.toMovie(), isFavorite = !isFavorite)) {
                isFavorite = !isFavorite
            }
        }
    }

}