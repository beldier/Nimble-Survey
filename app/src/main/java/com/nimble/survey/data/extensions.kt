package com.nimble.survey.data

import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException
import com.nimble.domain.AppError

fun Throwable.toError(): AppError = when (this) {
    is IOException -> AppError.Connectivity(message?:"")
    is HttpException -> AppError.Server(code())
    else -> AppError.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<AppError, T> = try {
    action().right()
} catch (e: Exception) {
    Log.e("Survey error", e.stackTraceToString())//TODO replace with timber
    e.toError().left()
}
