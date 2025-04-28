package eu.epfc.tmdb.network.interceptors

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val accessToken: String): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {
        return response.request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }

}