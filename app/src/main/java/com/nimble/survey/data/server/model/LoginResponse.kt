package com.nimble.survey.data.server.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("access_token"  ) var accessToken  : String,
    @SerializedName("token_type"    ) var tokenType    : String,
    @SerializedName("expires_in"    ) var expiresIn    : Int,
    @SerializedName("refresh_token" ) var refreshToken : String,
    @SerializedName("created_at"    ) var createdAt    : Int
)