package com.nimble.survey.data.server.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val data: Data,
)

data class Data(
    val id: String,
    val type: String,
    val attributes: Attributes,
)

data class Attributes(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("created_at")
    val createdAt: Long,
)
