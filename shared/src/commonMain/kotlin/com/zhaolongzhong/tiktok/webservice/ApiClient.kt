package com.zhaolongzhong.tiktok.webservice

import com.zhaolongzhong.tiktok.viewmodel.debugLogger
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiClient {

    private val client = HttpClient {}

    suspend fun getExampleResponse() {
        val response: HttpResponse = client.get("https://ktor.io/")
        debugLogger.log("getExampleResponse - ${response.status}")
    }
}
