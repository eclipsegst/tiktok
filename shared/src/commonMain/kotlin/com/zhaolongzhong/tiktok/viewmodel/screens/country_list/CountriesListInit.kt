package com.zhaolongzhong.tiktok.viewmodel.screens.country_list

import com.zhaolongzhong.tiktok.viewmodel.Navigation
import com.zhaolongzhong.tiktok.viewmodel.ScreenInitSettings

fun Navigation.initCountriesList() = ScreenInitSettings(
    title = "Country List",
    initState = { CountriesListState(isLoading = true) }
)
