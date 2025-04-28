package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json

data class Result(
    val success: Boolean? = null,
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "status_message")
    val statusMessage: String? = null,
    @Json(name = "error_message")
    val errorMessage: String? = null
)
