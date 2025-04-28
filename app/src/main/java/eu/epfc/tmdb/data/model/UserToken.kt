package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json

data class UserToken (
    @Json(name = "username")
    val userName: String,
    val password: String,
    @Json(name = "request_token")
    val requestToken: String
)