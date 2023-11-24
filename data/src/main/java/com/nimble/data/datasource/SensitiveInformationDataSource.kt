package com.nimble.data.datasource


interface SensitiveInformationDataSource {

    fun saveAuthToken(token: String)

    fun fetchAuthToken(): String?
}