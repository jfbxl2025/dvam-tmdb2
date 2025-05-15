package eu.epfc.tmdb.data.model

import com.squareup.moshi.Json

data class Review(
    val author: String = "",
    @Json(name = "author_details")
    val authorDetails: AuthorDetails = AuthorDetails(),
    val content: String = "",
    @Json(name = "created_at")
    val createdAt: String = "",
    val id: String = "",
    @Json(name = "updated_at")
    val updatedAt : String = "",
    val url: String = ""

)
{
    data class AuthorDetails(
        val name: String =  "",
        val username: String =  "",
        @Json(name = "avatar_path")
        val avatarPath: String? =  null,
        val rating: String? = null
    )
}
