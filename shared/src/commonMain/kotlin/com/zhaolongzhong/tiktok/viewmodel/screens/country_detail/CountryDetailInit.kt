package com.zhaolongzhong.tiktok.viewmodel.screens.country_detail

import com.zhaolongzhong.tiktok.viewmodel.Navigation
import com.zhaolongzhong.tiktok.viewmodel.ScreenInitSettings

fun Navigation.initCountryDetailInit() = ScreenInitSettings(
    title = "Country Detail",
    initState = { CountryDetailState(isLoading = true) }
)
