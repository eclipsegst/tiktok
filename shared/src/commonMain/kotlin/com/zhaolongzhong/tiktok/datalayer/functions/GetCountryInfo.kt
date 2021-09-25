package com.zhaolongzhong.tiktok.datalayer.functions

import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.datalayer.localdb.countries.getCountriesList
import com.zhaolongzhong.tiktok.viewmodel.screens.CountryInfo
import com.zhaolongzhong.tiktok.webservice.apis.fetchCountryExtraData

suspend fun Repository.getCountryInfo(country: String): CountryInfo = withRepoContext {

    webservices.fetchCountryExtraData(country)?.apply {
        sessionCache.countryExtraData[country] = data
    }

    CountryInfo(
        localDb.getCountriesList().first { it.name == country },
        sessionCache.countryExtraData[country],
    )
}