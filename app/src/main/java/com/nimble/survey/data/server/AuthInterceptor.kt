package com.nimble.survey.data.server

import com.nimble.data.SessionRepository
import com.nimble.survey.BuildConfig
import com.nimble.survey.data.server.models.GrantType
import com.nimble.survey.data.server.models.LoginResponse
import com.nimble.survey.data.server.models.RefreshBody
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionManager: SessionRepository,
    private val remoteConnection: RemoteConnection
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        if (response.code == 401) {
            val updatedAccessToken = runBlocking {
                val response = callRefreshTokenAPI()
                response?.let {
                    sessionManager.setAuthToken(it.data.attributes.accessToken)
                    sessionManager.setRefreshToken(it.data.attributes.refreshToken)
                }
                sessionManager.getAuthToken()
            }
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $updatedAccessToken")
                .build()
            return chain.proceed(newRequest)
        }
        return response
    }

    private suspend fun callRefreshTokenAPI(): LoginResponse? {
        return remoteConnection.authService.refreshToken(
            RefreshBody(
                grantType = GrantType.REFRESH_TOKEN.value,
                refreshToken = sessionManager.getRefreshToken() ?: "",
                clientId = BuildConfig.client_id,
                clientSecret = BuildConfig.client_secret

            )
        )
    }
}