package com.nimble.survey.data.server

import arrow.core.Either
import com.nimble.data.datasource.LoginDataSource
import com.nimble.domain.AppError
import com.nimble.domain.Login
import com.nimble.survey.BuildConfig
import com.nimble.survey.data.server.models.GrantType
import com.nimble.survey.data.server.models.LoginBody
import com.nimble.survey.data.server.models.LoginResponse
import com.nimble.survey.data.tryCall
import org.koin.core.annotation.Factory

@Factory
class LoginServerDataSource(private val remoteConnection: RemoteConnection) : LoginDataSource {
    override suspend fun login(email: String, password: String): Either<AppError, Login>  = tryCall  {
        remoteConnection.authService.loginWithEmail(
            LoginBody(
                grantType = GrantType.PASSWORD.value,
                email = email,
                password = password,
                clientId = BuildConfig.client_id,
                clientSecret = BuildConfig.client_secret
            )
        ).toDomainModel()
    }
}

private fun LoginResponse.toDomainModel() =
    Login(
        accessToken = this.data.attributes.accessToken,
        tokenType = this.data.attributes.tokenType,
        expiresIn = this.data.attributes.expiresIn,
        refreshToken = this.data.attributes.refreshToken,
        createdAt = this.data.attributes.createdAt
    )