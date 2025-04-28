package eu.epfc.tmdb.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.epfc.tmdb.Constants
import eu.epfc.tmdb.network.interceptors.MainInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object TmdbService {

    private const val API_KEY = Constants.TMDB_API_KEY
    private const val BASE_URL = Constants.TMDB_BASE_URL

    val moviesClient: MoviesClient
    val authClient: AuthClient

    private var sessionId: String? = null

    val getSessionId: () -> String? = { sessionId }
    val setSessionId: (String) -> Unit = { sessionId = it}

    init {
        val logger = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val mainInterceptor = MainInterceptor(API_KEY, getSessionId)
        val client = OkHttpClient.Builder()
            .addInterceptor(mainInterceptor)
            .addInterceptor(logger)

            .build()
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val jsonConverter = MoshiConverterFactory.create(moshi)

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(jsonConverter)
                .baseUrl(BASE_URL)
                .client(client)
        val retrofit = retrofitBuilder.build()

        moviesClient = retrofit.create(MoviesClient::class.java)
        authClient = retrofit.create(AuthClient::class.java)

    }
}