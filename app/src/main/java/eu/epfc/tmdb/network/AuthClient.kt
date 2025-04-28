package eu.epfc.tmdb.network

import eu.epfc.tmdb.data.model.RequestToken
import eu.epfc.tmdb.data.model.Session
import eu.epfc.tmdb.data.model.Token
import eu.epfc.tmdb.data.model.UserToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthClient {

    @GET("authentication/token/new ")
    suspend fun getRequestToken(): RequestToken

    @POST("authentication/token/validate_with_login")
    suspend fun validateWithLogin(@Body userToken: UserToken) : RequestToken

    @POST("authentication/session/new")
    suspend fun createSession(@Body token: Token): Session



}