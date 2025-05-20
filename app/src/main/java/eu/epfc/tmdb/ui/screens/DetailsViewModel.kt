package eu.epfc.tmdb.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import eu.epfc.tmdb.data.MoviesRepository
import eu.epfc.tmdb.data.model.Details
import eu.epfc.tmdb.data.model.Review
//import eu.epfc.tmdb.data.model.toMovie
import eu.epfc.tmdb.ui.DetailsDestination
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository:MoviesRepository,
) : ViewModel() {

    private val movieId = savedStateHandle.toRoute<DetailsDestination>().movieId


    var details: Details by mutableStateOf(Details())
    var isFavorite: Boolean by mutableStateOf( false)

    init {
        viewModelScope.launch {
            try {
                details = moviesRepository.getDetails(movieId)
                isFavorite = details.isFavorite
            }
            catch (e:Exception) {
                Log.e("details VM",e.message ?: "error")
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if( moviesRepository.setFavorite(details.movieId, !isFavorite) ) {
                isFavorite = !isFavorite
            }
        }
    }

}