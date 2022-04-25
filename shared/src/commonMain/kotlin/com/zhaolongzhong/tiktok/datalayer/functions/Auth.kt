package com.zhaolongzhong.tiktok.datalayer.functions

import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.webservice.apis.signIn
import com.zhaolongzhong.tiktok.webservice.apis.signUp

suspend fun Repository.signIn(username: String, password: String) = withRepoContext {
    webservices.signIn(username, password)
}

suspend fun Repository.signUp(username: String, password: String) = withRepoContext {
    webservices.signUp(username, password)
}
