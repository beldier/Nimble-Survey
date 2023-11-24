package com.nimble.data

import com.nimble.data.datasource.SensitiveInformationDataSource
import org.koin.core.annotation.Factory

@Factory
class SessionRepository (private val secureInformation: SensitiveInformationDataSource) {

    fun saveAuthToken(token: String) {
        secureInformation.saveAuthToken(token)
    }

    fun fetchAuthToken(): String? {
        return secureInformation.fetchAuthToken()
    }
}