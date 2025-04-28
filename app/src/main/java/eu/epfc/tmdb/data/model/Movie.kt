package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json

data class Movie(
   @Json(name = "genre_ids")
    val genreIds: List<Int> = emptyList(),
   @Json(name = "id")
    val movieId: Int = 0,
   @Json(name = "original_language")
    val originalLanguage: String = "",
   @Json(name = "original_title")
    val originalTitle: String = "",
   @Json(name = "overview")
    val overview: String = "",
   @Json(name = "popularity")
    val popularity: Double = 0.0,
   @Json(name = "poster_path")
    val posterPath: String?,
   @Json(name = "release_date")
    val releaseDate: String = "",
   @Json(name = "title")
    val title: String = "",
   @Json(name = "vote_average")
    val voteAverage: Double = 0.0,
   @Json(name = "vote_count")
    val voteCount: Int = 0,
    @Json(ignore = true)
    var isFavorite: Boolean = false
)

//   @Json(name = "adult") val adult: Boolean = false,
//   @Json(name = "backdrop_path") val backdropPath: String?,
//   @Json(name = "video") val video: Boolean = false,
