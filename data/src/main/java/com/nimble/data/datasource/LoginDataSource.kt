package com.nimble.data.datasource

import arrow.core.Either
import com.nimble.domain.AppError
import com.nimble.domain.Login

interface LoginDataSource {

    suspend fun login(email:String,password:String) : Either<AppError, Login>
}