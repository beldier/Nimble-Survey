package com.nimble.data.datasource


interface SensitiveInformationDataSource {

    fun setAuthToken(token: String)

    fun getAuthToken(): String?

    fun setRefreshToken(token:String)

    fun getRefreshToken():String?
}