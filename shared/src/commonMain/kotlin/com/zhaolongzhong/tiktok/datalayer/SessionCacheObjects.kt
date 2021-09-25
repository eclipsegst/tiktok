package com.zhaolongzhong.tiktok.datalayer

import com.zhaolongzhong.tiktok.datalayer.objects.CountryExtraData
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SessionCacheObjects {
    // here is the repository data we decide to just cache temporarily (for the runtime session),
    // rather than caching it permanently in the local db

    internal val countryExtraData: MutableMap<String, CountryExtraData> by lazy { mutableMapOf() }
}
