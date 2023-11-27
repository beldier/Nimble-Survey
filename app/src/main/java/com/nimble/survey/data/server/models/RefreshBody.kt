package com.nimble.survey.data.server.models

import com.google.gson.annotations.SerializedName

data class RefreshBody(
    @SerializedName("grant_type"    ) var grantType    : String,
    @SerializedName("refresh_token" ) var refreshToken : String,
    @SerializedName("client_id"     ) var clientId     : String,
    @SerializedName("client_secret" ) var clientSecret : String
)