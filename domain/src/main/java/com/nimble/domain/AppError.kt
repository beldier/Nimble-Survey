package com.nimble.domain

sealed class AppError(val message:String) {
    class Server(private val code: Int) : AppError("Server error:$code")
    class Connectivity(val text:String) : AppError("Connectivity error") // TODO enhance message errors for connectivity
    class Unknown(val appError: String) : AppError("Unknown error:  $appError")
}