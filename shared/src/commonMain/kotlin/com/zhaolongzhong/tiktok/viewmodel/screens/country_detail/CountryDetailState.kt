package com.zhaolongzhong.tiktok.viewmodel.screens.country_detail

import com.zhaolongzhong.tiktok.datalayer.objects.CountryExtraData
import com.zhaolongzhong.tiktok.datalayer.objects.CountryListData
import com.zhaolongzhong.tiktok.viewmodel.ScreenState

data class CountryDetailState(
    val isLoading: Boolean = false,
    val countryInfo: CountryInfo = CountryInfo(),
) : ScreenState

data class CountryInfo(
    val _listData: CountryListData = CountryListData(),
    val _extraData: CountryExtraData? = CountryExtraData(),
) {
    val population = _listData.population.toString()
    val firstDoses = _listData.firstDoses.toString()
    val firstDosesPerc = _listData.firstDosesPercentageFloat.toString()
    val fullyVaccinated = _listData.fullyVaccinated.toString()
    val fullyVaccinatedPerc = _listData.fullyVaccinatedPercentageFloat.toString()
    val vaccinesList: List<String>? = _extraData?.vaccinesList
}