package com.zhaolongzhong.tiktok.webservice

import com.zhaolongzhong.tiktok.viewmodel.debugLogger
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiClient {

    val baseUrl = "https://covidvax.org"

    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getExampleResponse() {
        val response: HttpResponse = client.get("https://ktor.io/")
        debugLogger.log("getExampleResponse - ${response.status}")
    }

    suspend inline fun <reified T : Any> getResponse(endpoint: String): T? {
        val url = baseUrl + endpoint

        try {
            // please notice, Ktor Client is switching to a background thread under the hood
            // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
            val response = client.get<T>(url)
            debugLogger.log("$url API SUCCESS")
            return response
        } catch (e: Exception) {
            debugLogger.log("$url API FAILED: " + e.message)
        }
        return null
    }
}
