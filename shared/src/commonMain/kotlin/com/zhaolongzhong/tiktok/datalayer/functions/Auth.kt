package com.zhaolongzhong.tiktok.datalayer.functions

import com.zhaolongzhong.tiktok.datalayer.*
import com.zhaolongzhong.tiktok.webservice.apis.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

private const val AccessTokenResponseKey = "AccessTokenResponse"
suspend fun Repository.signIn(username: String, password: String) = withRepoContext {
    webservices.signIn(username, password).let {
        localDb.keyValuesQueries.upsertKeyValue(AccessTokenResponseKey, Json.encodeToString(it))
        val res = getAccessToken()
        print("accessToken: $res")
    }
}

fun Repository.getAccessToken(): AccessTokenResponse? {
    localDb.keyValuesQueries.getKeyValueByKey(AccessTokenResponseKey).executeAsOneOrNull()?.let { keyValue ->
        val response = keyValue.content?.let {
            Json.decodeFromString<AccessTokenResponse>(it)
        }
        return response
    }

    return null
}

suspend fun Repository.signUp(username: String, password: String) = withRepoContext {
    webservices.signUp(username, password)
}
