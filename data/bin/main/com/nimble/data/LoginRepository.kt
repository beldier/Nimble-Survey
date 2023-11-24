package com.nimble.data

import com.nimble.data.datasource.LoginDataSource
import org.koin.core.annotation.Factory

@Factory
class LoginRepository(private val loginDataSource: LoginDataSource) {

    suspend fun performLogin(email:String,password:String){
        loginDataSource.login(email, password)
    }
}