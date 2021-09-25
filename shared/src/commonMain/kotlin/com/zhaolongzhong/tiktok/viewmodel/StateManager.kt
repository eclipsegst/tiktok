package com.zhaolongzhong.tiktok.viewmodel

import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.datalayer.functions.getCountriesListData
import com.zhaolongzhong.tiktok.datalayer.functions.getCountryInfo
import com.zhaolongzhong.tiktok.viewmodel.screens.CountriesListState
import com.zhaolongzhong.tiktok.viewmodel.screens.CountryDetailState
import com.zhaolongzhong.tiktok.viewmodel.screens.CountryInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StateManager(private val repo: Repository) {
    var countryListScreenState =
        MutableStateFlow(CountriesListState(isLoading = true, countriesListItems = emptyList()))

    var detailState =
        MutableStateFlow(CountryDetailState(isLoading = true, countryInfo = CountryInfo()))

    init {
        // TODO: handle coroutine scope properly
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val result = repo.getCountriesListData()
            countryListScreenState.value =
                CountriesListState(isLoading = false, countriesListItems = result)
        }
    }

    fun getCountryIn(name: String) {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val result = repo.getCountryInfo(name)
            detailState.value = detailState.value.copy(isLoading = false, countryInfo = result)
        }
    }
}
