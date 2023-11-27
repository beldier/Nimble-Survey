package com.nimble.survey.data.server

import com.nimble.survey.data.server.models.LoginBody
import com.nimble.survey.data.server.models.LoginResponse
import com.nimble.survey.data.server.models.RefreshBody
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("oauth/token")
    suspend fun loginWithEmail(
        @Body loginBody: LoginBody
    ): LoginResponse

    @POST("oauth/token")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshBody): LoginResponse
}