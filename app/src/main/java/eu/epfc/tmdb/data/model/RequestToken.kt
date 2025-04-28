package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json


data class RequestToken(
    val success: Boolean,
    @Json(name = "expires_at")
    val expiresAt:  String,
    @Json(name = "request_token")
    val value: String
)

data class Token (
    @Json(name = "request_token")
    val value: String
)
