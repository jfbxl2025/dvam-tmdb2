package eu.epfc.tmdb.data

import eu.epfc.tmdb.data.model.Details
import eu.epfc.tmdb.data.model.Details.Genre
import eu.epfc.tmdb.data.model.Movie

data object Mock {
    val movie = Movie(
        title = "A Working Man",
        posterPath = null,
        overview =  "Levon Cade left behind a decorated military career in the black ops to live a simple life working construction. But when his boss's daughter, who is like family to him, is taken by human traffickers, his search to bring her home uncovers a world of corruption far greater than he ever could have imagined.",
        isFavorite = true
    )
    val genres: List<Genre> = listOf(
        Genre(name = "Drama"),
        Genre(name = "Action"),
        Genre(name = "Thriller"),
        Genre(name = "Drama"),
        Genre(name = "Action"),
        Genre(name = "Thriller"),
    )

    val details = Details(
        title = "A Working Man",
        posterPath = null,
        overview =  "Levon Cade left behind a decorated military career in the black ops to live a simple life working construction. But when his boss's daughter, who is like family to him, is taken by human traffickers, his search to bring her home uncovers a world of corruption far greater than he ever could have imagined.",
        isFavorite = true,
        listGenres = genres,
        releaseDate = "2024-09-12",
        tagline = "Une incroyable épopée au cœur de la nature."
    )



}

