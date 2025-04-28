package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json

data class Favorite(
    @Json(name = "media_type")
    val mediaType: String = "movie",
    @Json(name = "media_id")
    val movieId: Int,
    @Json(name = "favorite")
    val isFavorite: Boolean = false
)


