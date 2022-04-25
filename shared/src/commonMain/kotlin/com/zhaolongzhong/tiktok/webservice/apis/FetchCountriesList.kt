package com.zhaolongzhong.tiktok.webservice.apis

import com.zhaolongzhong.tiktok.datalayer.objects.CountryListData
import com.zhaolongzhong.tiktok.webservice.ApiClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

suspend fun ApiClient.fetchCountriesList(): CountriesListResponse? {
    return getResponse(baseUrlCovid, "/dkmpl/")
}

@Serializable
data class CountriesListResponse(
    @SerialName("data") val data: List<CountryListData>,
    @SerialName("err") val error: String? = null,
)
