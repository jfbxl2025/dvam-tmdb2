package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json

data class Session(
    val success: Boolean,
    @Json(name = "session_id")
    val sessionId: String? = null
)
