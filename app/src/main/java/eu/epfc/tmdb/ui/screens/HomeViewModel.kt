package eu.epfc.tmdb.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.epfc.tmdb.data.services.AuthManager
import kotlinx.coroutines.launch

class HomeViewModel (private val authManager: AuthManager): ViewModel() {

    var isAuthenticated  =  mutableStateOf(authManager.isAuthenticated)

    val currentName: String = authManager.currentName?:""
    val currentPassword: String = authManager.currentPassword?:""


    fun connect() = authManager.connect()

    fun login(userName: String, userPassword: String) {
        viewModelScope.launch {
            isAuthenticated.value = authManager.login(userName = userName, userPassword = userPassword)

        }
    }
}