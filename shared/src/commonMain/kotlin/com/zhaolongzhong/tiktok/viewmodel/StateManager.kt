package com.zhaolongzhong.tiktok.viewmodel

import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.datalayer.functions.getCountriesListData
import com.zhaolongzhong.tiktok.datalayer.functions.getCountryInfo
import com.zhaolongzhong.tiktok.datalayer.functions.signIn
import com.zhaolongzhong.tiktok.datalayer.functions.signUp
import com.zhaolongzhong.tiktok.viewmodel.screens.country_detail.CountryDetailState
import com.zhaolongzhong.tiktok.viewmodel.screens.country_detail.CountryInfo
import com.zhaolongzhong.tiktok.viewmodel.screens.country_list.CountriesListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StateManager(private val repo: Repository) {
    internal val mutableStateFlow = MutableStateFlow(AppState())

    var countryListScreenState =
        MutableStateFlow(CountriesListState(isLoading = true, countriesListItems = emptyList()))

    var detailState =
        MutableStateFlow(CountryDetailState(isLoading = true, countryInfo = CountryInfo()))

    init {
        getCountryList()
    }

    fun triggerRecomposition() {
        debugLogger.log("triggerRecomposition")
        mutableStateFlow.value = AppState(mutableStateFlow.value.recompositionIndex+1)
    }

    fun cancelScreenScopes() {
        debugLogger.log("cancelScreenScopes")
    }

    private fun launchWithDefaultScope(block: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(Job() + Dispatchers.Main).launch(block = block)
    }

    fun getCountryList() {
        launchWithDefaultScope {
            val result = repo.getCountriesListData()
            countryListScreenState.value =
                CountriesListState(isLoading = false, countriesListItems = result)
            triggerRecomposition()
        }
    }

    fun setDetailState(countryDetailState: CountryDetailState) {
        detailState.value = countryDetailState
    }

    fun getCountryInfo(name: String) {
        launchWithDefaultScope {
            val result = repo.getCountryInfo(name)
            detailState.value = detailState.value.copy(isLoading = false, countryInfo = result)
        }
    }

    fun signUp(username: String, password: String) {
        launchWithDefaultScope {
            repo.signUp(username= username, password = password)
        }
    }

    fun signIn(username: String, password: String) {
        launchWithDefaultScope {
            repo.signIn(username= username, password = password)
        }
    }
}

data class AppState(
    val recompositionIndex : Int = 0,
)
