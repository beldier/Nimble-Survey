package com.nimble.survey.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimble.usescases.PerformLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel (
    private val performLoginUseCase: PerformLoginUseCase
): ViewModel(){

    private val _dataState = MutableStateFlow(DataState())
    val dataState: StateFlow<DataState> = _dataState.asStateFlow()
    data class DataState(
        val email: String = "",
        val password: String = "",
    )

    fun performLogin(){
        viewModelScope.launch {
            performLoginUseCase(_dataState.value.email,_dataState.value.password)
        }
    }

    fun updateEmail(email:String){
        _dataState.update {
            _dataState.value.copy(email = email,)
        }
    }

    fun updatePassword(password:String){
        _dataState.update {
            _dataState.value.copy(password = password,)
        }
    }
}