package com.zhaolongzhong.tiktok.webservice

import com.zhaolongzhong.tiktok.viewmodel.debugLogger
import io.ktor.client.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*

class ApiClient {

    val baseUrlCovid = "https://covidvax.org"
    val baseUrl = "http://localhost:8080"

    private val clientId = "client"
    private val clientSecret = "secret"
    @OptIn(InternalAPI::class)
    val basicToken: String = "$clientId:$clientSecret".encodeBase64()

    val jsonClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    val authClient = jsonClient.config {
        install(Auth) {
        }
    }

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T : Any> request(httpRequestBuilder: HttpRequestBuilder): T? {
        val url = httpRequestBuilder.url
        try {
            // please notice, Ktor Client is switching to a background thread under the hood
            // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
            val response = authClient.request<T>(httpRequestBuilder)
            debugLogger.log("$url API SUCCESS: $response")
            return response
        } catch (e: Exception) {
            debugLogger.log("$url API FAILED: " + e.message)
        }

        return null
    }

    suspend fun getExampleResponse() {
        val response: HttpResponse = jsonClient.get("https://ktor.io/")
        debugLogger.log("getExampleResponse - ${response.status}")
    }

    suspend inline fun <reified T : Any> getResponse(endpoint: String): T? {
        return getResponse(baseUrl, endpoint)
    }

    suspend inline fun <reified T : Any> getResponse(baseUrl: String, endpoint: String): T? {
        val url = baseUrl + endpoint

        try {
            // please notice, Ktor Client is switching to a background thread under the hood
            // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
            val response = jsonClient.get<T>(url)
            debugLogger.log("$url API SUCCESS")
            return response
        } catch (e: Exception) {
            debugLogger.log("$url API FAILED: " + e.message)
        }
        return null
    }
}
