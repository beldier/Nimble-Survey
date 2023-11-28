package com.nimble.domain

data class Login(
    var accessToken: String,
    var tokenType: String,
    var expiresIn: Long,
    var refreshToken: String,
    var createdAt: Long
)