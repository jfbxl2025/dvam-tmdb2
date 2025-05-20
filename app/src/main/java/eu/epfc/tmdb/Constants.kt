package eu.epfc.tmdb

object Constants {
//    const val TMDB_API_KEY =  "e06eb402091615e803b7638fb4568c97"
//    const val TMDB_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDZlYjQwMjA5MTYxNWU4MDNiNzYzOGZiNDU2OGM5NyIsIm5iZiI6MTc0MzU4NzIwMS4xNjcsInN1YiI6IjY3ZWQwNzgxM2Q4ZTc0ZTUxODAxMmJjOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pOJ9tUlQlNPbttiQpj8sQw-vXZn8E0UI88rsLspFT4g"
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"

    private const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/"
    enum class TMDB_IMAGE_SIZE(val path: String) {
        THUMB("${TMDB_IMAGE_URL}w185"),
        SMALL("${TMDB_IMAGE_URL}w342"),
        MEDIUM("${TMDB_IMAGE_URL}w500"),
        LARGE("${TMDB_IMAGE_URL}w780"),
        ORIGINAL("${TMDB_IMAGE_URL}original")
    }
}