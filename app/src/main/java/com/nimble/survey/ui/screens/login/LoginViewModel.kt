package com.nimble.survey.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimble.domain.AppError
import com.nimble.usescases.PerformLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val performLoginUseCase: PerformLoginUseCase
) : ViewModel() {

    private val _dataState = MutableStateFlow(DataState())
    val dataState: StateFlow<DataState> = _dataState.asStateFlow()

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    data class DataState(
        val email: String = "",
        val password: String = "",
    )

    data class UIState(
        val appError: AppError? = null
    )

    fun performLogin(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            performLoginUseCase(_dataState.value.email, _dataState.value.password).fold(
                ifLeft = {
                    updateError(it)
                },
                ifRight = {
                    onLoginSuccess()
                }
            )
        }
    }

    fun updateError(appError: AppError?) {
        _uiState.update {
            _uiState.value.copy(appError = appError)
        }
    }

    fun dismissDialog() {
        _uiState.update {
            _uiState.value.copy(appError = null)
        }
    }

    fun updateEmail(email: String) {
        _dataState.update {
            _dataState.value.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _dataState.update {
            _dataState.value.copy(password = password)
        }
    }
}