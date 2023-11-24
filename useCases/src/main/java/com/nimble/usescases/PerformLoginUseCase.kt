package com.nimble.usescases

import com.nimble.data.LoginRepository
import com.nimble.data.SessionRepository
import org.koin.core.annotation.Factory

@Factory
class PerformLoginUseCase(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(email: String, password: String) =
        loginRepository.performLogin(email, password)
}