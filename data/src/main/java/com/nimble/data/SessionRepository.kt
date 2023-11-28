package com.nimble.data

import com.nimble.data.datasource.SensitiveInformationDataSource
import org.koin.core.annotation.Factory

@Factory
class SessionRepository (private val secureInformation: SensitiveInformationDataSource) {

    fun setAuthToken(token: String) {
        secureInformation.setAuthToken(token)
    }

    fun setRefreshToken(token:String){
        secureInformation.setRefreshToken(token)
    }

    fun getRefreshToken(): String? {
        return secureInformation.getRefreshToken()
    }


    fun getAuthToken(): String? {
        return secureInformation.getAuthToken()
    }
}