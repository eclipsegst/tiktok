package com.zhaolongzhong.tiktok.datalayer

import com.zhaolongzhong.tiktok.webservice.ApiClient

class Repository {
    val webservices by lazy { ApiClient() } // TODO: make internal
}