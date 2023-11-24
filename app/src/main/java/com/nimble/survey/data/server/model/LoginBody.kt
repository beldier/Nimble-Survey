package com.nimble.survey.data.server.model

import com.google.gson.annotations.SerializedName

data class LoginBody (
    @SerializedName("grant_type") val grantType: String = GrantType.PASSWORD.value,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String
)

enum class GrantType(val value:String){
    PASSWORD("password")
}