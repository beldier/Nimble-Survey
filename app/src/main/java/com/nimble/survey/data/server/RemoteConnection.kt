package com.nimble.survey.data.server

import android.content.Context
import com.nimble.data.SessionRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Single
class RemoteConnection(private val sessionManager: SessionRepository) {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this)
            .addInterceptor(AuthInterceptor(sessionManager, this@RemoteConnection)).build()
    }

    private val builder = Retrofit.Builder()
        .baseUrl("https://survey-api.nimblehq.co/api/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val remoteService: RemoteService = builder.create()
    val authService: AuthService = builder.create()


}