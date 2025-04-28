package eu.epfc.tmdb.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class MainInterceptor(private val apiKey: String, private val getSessionId: () -> String?): Interceptor {

    companion object {
        private const val PARAM_API_KEY = "api_key"
        private const val PARAM_SESSION_ID = "session_id"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val sessionId = getSessionId()
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(PARAM_API_KEY, apiKey)
            .also { if (sessionId != null) it.addQueryParameter(PARAM_SESSION_ID, sessionId) }
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return  chain.proceed(newRequest)
    }
}