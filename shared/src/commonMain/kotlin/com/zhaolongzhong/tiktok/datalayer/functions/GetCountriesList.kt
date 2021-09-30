package com.zhaolongzhong.tiktok.datalayer.functions

import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.datalayer.localdb.countries.getCountriesList
import com.zhaolongzhong.tiktok.datalayer.localdb.countries.setCountriesList
import com.zhaolongzhong.tiktok.viewmodel.debugLogger
import com.zhaolongzhong.tiktok.viewmodel.screens.country_list.CountriesListItem
import com.zhaolongzhong.tiktok.webservice.apis.fetchCountriesList

suspend fun Repository.getCountriesListData(): List<CountriesListItem> = withRepoContext {

    // Step 1: Fetch data from remote
    webservices.fetchCountriesList()?.apply {
        debugLogger.log("countriesList FETCHED FROM WEBSERVICES")
        if (error == null) {
            // Step 2: Save data to local database
            localDb.setCountriesList(data.sortedByDescending { it.firstDosesPercentageFloat })
        } else {
            debugLogger.log("ERROR MESSAGE: $error")
        }
    }

    // Step 3: Return a list latest data from local database
    localDb.getCountriesList().map { elem ->
        CountriesListItem(_data = elem)
    }.toList()
}
