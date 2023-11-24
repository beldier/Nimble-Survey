package com.nimble.data.datasource

interface LoginDataSource {

    suspend fun login(email:String,password:String)
}