package com.nimble.survey.data.server.model

import com.nimble.data.datasource.LoginDataSource
import com.nimble.survey.BuildConfig
import com.nimble.survey.data.server.RemoteConnection
import org.koin.core.annotation.Factory

@Factory
class LoginServerDataSource(private val remoteConnection: RemoteConnection) : LoginDataSource {
    override suspend fun login(email: String, password: String) {
        remoteConnection.authService.loginWithEmail(
            LoginBody(
                grantType = GrantType.PASSWORD.value,
                email = email,
                password = password,
                clientId = BuildConfig.client_id,
                clientSecret = BuildConfig.client_secret
            )
        )
    }
}