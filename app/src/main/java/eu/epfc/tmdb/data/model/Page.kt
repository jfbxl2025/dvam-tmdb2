package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json



data class Page<T>(
   @Json(name = "page")
    val page: Int,
   @Json(name = "results")
    val results: List<T>,
   @Json(name = "total_pages") val totalPages: Int,
   @Json(name = "total_results") val totalResults: Int
)
{
    fun hasNext() = page < totalPages
}