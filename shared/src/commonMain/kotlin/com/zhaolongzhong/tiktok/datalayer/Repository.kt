package com.zhaolongzhong.tiktok.datalayer

import com.squareup.sqldelight.db.SqlDriver
import com.zhaolongzhong.tiktok.webservice.ApiClient
import mylocal.db.LocalDb

class Repository(val sqlDriver: SqlDriver) {
    val webservices by lazy { ApiClient() } // TODO: make internal
    internal val localDb by lazy { LocalDb(sqlDriver) }
}