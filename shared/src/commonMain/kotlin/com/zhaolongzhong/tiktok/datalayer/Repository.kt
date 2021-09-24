package com.zhaolongzhong.tiktok.datalayer

import com.squareup.sqldelight.db.SqlDriver
import com.zhaolongzhong.tiktok.webservice.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mylocal.db.LocalDb

class Repository(val sqlDriver: SqlDriver, val useDefaultDispatcher: Boolean = true) {
    val webservices by lazy { ApiClient() } // TODO: make internal
    internal val localDb by lazy { LocalDb(sqlDriver) }

    // we run each repository function on a Dispatchers.Default coroutine
    // we pass useDefaultDispatcher=false just for the TestRepository instance
    suspend fun <T> withRepoContext (block: suspend () -> T) : T {
        return if (useDefaultDispatcher) {
            withContext(Dispatchers.Default) {
                block()
            }
        } else {
            block()
        }
    }
}
