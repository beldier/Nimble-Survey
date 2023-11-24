package com.nimble.survey.data.server

import com.nimble.survey.data.server.model.LoginBody
import com.nimble.survey.data.server.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthService {
    @POST("oauth/token")
    suspend fun loginWithEmail(
        @Body loginBody: LoginBody
    ): LoginResponse
}