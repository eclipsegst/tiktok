package com.zhaolongzhong.tiktok.webservice.apis

import com.zhaolongzhong.tiktok.webservice.ApiClient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalAPI::class)
suspend fun ApiClient.signIn(username: String, password: String): AccessTokenResponse? {
    val url = "http://localhost:8080/oauth/token"
    val clientId = "client"
    val clientSecret = "secret"
    val basicToken = "$clientId:$clientSecret".encodeBase64()

    val httpRequestBuilder = HttpRequestBuilder().apply {
        url(url)
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
//    val url = "http://localhost:8080/auth/signup"
//    val clientId = "client"
//    val clientSecret = "secret"
//    val basicToken = "$clientId:$clientSecret".encodeBase64()
//
//    val httpRequestBuilder = HttpRequestBuilder().apply {
//        url(url)
//        method = HttpMethod.Post
//        headers {
//            append(HttpHeaders.Authorization, "Basic $basicToken")
//            append(HttpHeaders.Accept, "application/json")
//        }
//
//        parameter("grant_type", "password")
//        parameter("username", username)
//        parameter("password", password)
//    }
//
//    request<AccessTokenResponse>(httpRequestBuilder)
}
