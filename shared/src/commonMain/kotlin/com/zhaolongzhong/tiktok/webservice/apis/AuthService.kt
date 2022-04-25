package com.zhaolongzhong.tiktok.webservice.apis

import com.zhaolongzhong.tiktok.webservice.ApiClient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Auth flow:
// Make sure to have a client credential to make a request
// A client credential is to authorize a client to perform any request,
// either when the user is not login or the user is log in.
//
// When making an request, we need
// 1> client credential for any request
// 2> user credential for initial login,
// once we have initial login, we will have access token,
// then we will use client credential + access token for a login user

@OptIn(InternalAPI::class)
suspend fun ApiClient.signIn(username: String, password: String): AccessTokenResponse? {
    val httpRequestBuilder = HttpRequestBuilder().apply {
        url("$baseUrl/oauth/token")
        method = HttpMethod.Post
        headers {
            append(HttpHeaders.Authorization, "Basic $basicToken")
            append(HttpHeaders.Accept, "application/json")
        }

        parameter("grant_type", "password")
        parameter("username", username)
        parameter("password", password)
    }

    return request<AccessTokenResponse>(httpRequestBuilder)
}

@Serializable
data class AccessTokenResponse(
    @SerialName("access_token") val accessToken : String = "",
    @SerialName("token_type") val tokenType : String = "",
    @SerialName("refresh_token") val refreshToken : String = "",
    @SerialName("expires_in") val expiresIn : Long = 0,
    @SerialName("scope") val scope : String = "",
    @SerialName("jti") val jti : String = "",
)

@OptIn(InternalAPI::class)
suspend fun ApiClient.signUp(username: String, password: String) {
    val httpRequestBuilder = HttpRequestBuilder().apply {
        url("$baseUrl/auth/signup")
        method = HttpMethod.Post
        headers {
            append(HttpHeaders.Authorization, "Basic $basicToken")
            append(HttpHeaders.ContentType, "application/json")
        }

        body = SignUpRequest(username = username, password = password, enabled = true)
    }

    request<Unit>(httpRequestBuilder)
}

@Serializable
data class SignUpRequest(
    val username: String,
    val password: String,
    val enabled: Boolean,
)
