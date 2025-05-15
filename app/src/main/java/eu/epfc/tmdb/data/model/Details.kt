package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json




data class Details(
    @Json(name = "backdrop_path")
    val backdropPath: String = "",
    @Json(name = "genres")
    val listGenres: List<Genre> = emptyList(),
    @Json(name = "homepage")
    val homepage: String? = null,
    @Json(name = "id")
    val movieId: Int = 0,
    @Json(name = "original_language")
    val originalLanguage: String = "",
    @Json(name = "original_title")
    val originalTitle: String = "",
    @Json(name = "overview")
    val overview: String = "",
    @Json(name = "popularity")
    val popularity: Float = 0.0F,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "release_date")
    val releaseDate: String = "",
    @Json(name = "tagline")
    val tagline: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "vote_average")
    val voteAverage: Float = 0.0F,
    @Json(name = "vote_count")
    val voteCount: Int = 0,
    @Json(ignore = true)
    var isFavorite: Boolean = false
)
{
    data class Genre(
        val id: Int = 0,
        val name: String = "",
    )


    val year : String
        get()  =  if (releaseDate.length > 3 ) releaseDate.substring(0..3)  else "?"

    val genres: List<String>
        get() = listGenres.map { it.name }

    //data class Language(
    //    @Json(name = "english_name")  val englishName: String = "",
    //    @Json(name = "iso_639_1") val iso639: String = "",
    //    @Json(name = "name") val name: String = "",
    //)

    //data class Country(
    //    @Json(name = "iso_3166_1") val iso3166: String,
    //    val name: String
    //)

    //data class Company(
    //    @Json(name = "id")  val id: Int,
    //    @Json(name = "logo_path") val logoPath: String = "",
    //    @Json(name = "name") val name: String = "",
    //    @Json(name = "origin_country") val originCountry: String = ""
    //)

}

//    @Json(name = "adult")val adult: Boolean = false,
//    @Json(name = "budget") val budget: Long = 0,
//    @Json(name = "imdb_id") val imdbId: String? = null,
//    @Json(name = "production_companies") val productionCompanies: List<Company>? = null,
//    @Json(name = "production_countries") val productionCountries: List<Country>? = null,
//    @Json(name = "revenue") val revenue: Long = 0,
//    @Json(name = "runtime") val runtime: Int? = null,
//    @Json(name = "spoken_languages") val spokenLanguages: List<Language> = emptyList(),
//    @Json(name = "status") val status: String = "",
//    @Json(name = "video") val video: Boolean = false,
//    @Json(ignore = true) var isFavorite: Boolean = false





