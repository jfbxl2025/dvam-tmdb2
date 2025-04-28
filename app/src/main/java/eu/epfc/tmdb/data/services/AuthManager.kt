package eu.epfc.tmdb.data.services

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import eu.epfc.tmdb.network.AuthClient
import eu.epfc.tmdb.network.TmdbService
import eu.epfc.tmdb.data.model.RequestToken
import eu.epfc.tmdb.data.model.Token
import eu.epfc.tmdb.data.model.UserToken

class AuthManager(context: Context, private val authClient: AuthClient = TmdbService.authClient, private val setSessionId : (String) -> Unit) {

    private companion object {
        const val USER_NAME = "user_name"
        const val USER_PASSWORD = "user_password"
        const val IS_AUTHENTICATED = "is_authenticated"
        const val SESSION_ID = "session_id"
    }

    private val sharedPreference: SharedPreferences = context.getSharedPreferences("LoggedIn", Context.MODE_PRIVATE)

    val currentName: String?
        get() = sharedPreference.getString(USER_NAME, "")

    val currentPassword: String?
        get() = sharedPreference.getString(USER_PASSWORD, "")

    val isAuthenticated: Boolean
        get() = sharedPreference.getBoolean(IS_AUTHENTICATED, false)

    val sessionId: String?
        get() = sharedPreference.getString(SESSION_ID, "")


    fun connect(): Boolean {
        Log.i("AuthManager","connect")
        return if(sessionId != null) {
            setSessionId(sessionId!!)
            true
        } else false

    }

    
    suspend fun login(userName: String, userPassword: String): Boolean {
        Log.i("AuthManager","login")

        val requestToken = authClient.getRequestToken()

        return if (requestToken.success) {

            val validateToken = tokenValidation(userName, userPassword, requestToken)
            if (validateToken?.success == true) {
                val sessionId = createSession(validateToken)
                if (sessionId != null) {
                    sharedPreference.edit()
                        .putString(USER_NAME, userName)
                        .putString(USER_PASSWORD, userPassword)
                        .putString(SESSION_ID, sessionId)
                        .putBoolean(IS_AUTHENTICATED, true)
                        .apply()
                    true
                }
                else false
            }
            else false
        }
        else false
    }

    private suspend fun tokenValidation(userName: String, userPassword: String, requestToken: RequestToken): RequestToken? {
        return try {
            authClient.validateWithLogin(
                UserToken(
                    userName = userName,
                    password = userPassword,
                    requestToken = requestToken.value
                )
            )
        } catch (e:Exception) {
            Log.e("invalidate token",e.message ?: "can't validate the request token $requestToken")
            null
        }
    }

    private suspend fun createSession(validateToken: RequestToken): String? {
        return try {
            val session = authClient.createSession(Token(value = validateToken.value))
            if (session.sessionId != null ) {
                setSessionId(session.sessionId)
                session.sessionId
            } else null
        } catch (e:Exception) {
            Log.e("can't create session",e.message ?: "error ???")
            null
        }
    }

}
