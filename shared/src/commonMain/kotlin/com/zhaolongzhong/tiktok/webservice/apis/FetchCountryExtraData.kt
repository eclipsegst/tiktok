package com.zhaolongzhong.tiktok.webservice.apis

import com.zhaolongzhong.tiktok.datalayer.objects.CountryExtraData
import com.zhaolongzhong.tiktok.webservice.ApiClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

suspend fun ApiClient.fetchCountryExtraData(country: String): CountryExtraResponse? {
    return getResponse(baseUrlCovid, "/dkmpd/" + country.replace(" ", "_"))
}

@Serializable
data class CountryExtraResponse(
    @SerialName("data") val data: CountryExtraData,
    @SerialName("err") val error: String? = null,
)
