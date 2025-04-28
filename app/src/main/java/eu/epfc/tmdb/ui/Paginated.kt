package eu.epfc.tmdb.ui


interface Paginated {

    var page: Int
    var pagesCount: Int

    val hasNext
        get() = page < pagesCount

    fun getPrev()
    fun getNext()

}


