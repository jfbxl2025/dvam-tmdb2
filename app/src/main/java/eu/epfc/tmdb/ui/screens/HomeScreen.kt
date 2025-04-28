package eu.epfc.tmdb.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import eu.epfc.tmdb.ui.TmdbViewModelProvider
import eu.epfc.tmdb.ui.components.TmdbScaffold




@Composable
fun HomeScreen (
    navigateToConnected: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = TmdbViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    TmdbScaffold(
        title = "Home screen"
    ) {
        if (homeViewModel.isAuthenticated.value) {
            if (homeViewModel.connect()) navigateToConnected()
        } else {
            Login(authViewModel = homeViewModel, navigateToConnected = navigateToConnected)
        }
        Button( onClick = navigateToConnected) { Text("Go")}

    }


}

@Composable
fun Login(
    authViewModel: HomeViewModel,
    navigateToConnected: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(modifier = Modifier
        .fillMaxHeight()
    ){

        val userName = remember { mutableStateOf(authViewModel.currentName) }
        val userPassword = remember { mutableStateOf(authViewModel.currentPassword) }

        LaunchedEffect(authViewModel.isAuthenticated.value)  {
            if (authViewModel.isAuthenticated.value) {
                navigateToConnected()
            }
        }

        Text(
            text = "Hello,\nWelcome to the login page",
            fontSize = 25.sp,
//                color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 0.dp)
        )

        // Username input field
        OutlinedTextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = "username")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp)
        )

        // Password input field
        OutlinedTextField(
            value = userPassword.value,
            onValueChange = { userPassword.value = it},
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "password")
            },
            label = {
                Text(text = "password")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        // Login button
        OutlinedButton(onClick = { authViewModel.login(userName.value, userPassword.value) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp)) {
            Text(text = "Login",
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }

}





