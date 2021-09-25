package com.zhaolongzhong.tiktok.viewmodel

import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.datalayer.functions.getCountriesListData
import com.zhaolongzhong.tiktok.viewmodel.screens.CountriesListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StateManager(repo: Repository) {
    var countryListScreenState =
        MutableStateFlow(CountriesListState(isLoading = true, countriesListItems = emptyList()))

    init {
        // TODO: handle coroutine scope properly
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val result = repo.getCountriesListData()
            countryListScreenState.value =
                CountriesListState(isLoading = false, countriesListItems = result)
        }
    }
}
