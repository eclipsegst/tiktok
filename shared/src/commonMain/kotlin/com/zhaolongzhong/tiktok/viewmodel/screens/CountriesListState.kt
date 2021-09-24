package com.zhaolongzhong.tiktok.viewmodel.screens

import com.zhaolongzhong.tiktok.datalayer.objects.CountryListData

data class CountriesListState (
    val isLoading: Boolean = false,
    val countriesListItems : List<CountriesListItem> = emptyList()
)

data class CountriesListItem (
    val _data : CountryListData,
) {
    // in the ViewModel classes, our computed properties only do UI-formatting operations
    // (the arithmetical operations, such as calculating a percentage, should happen in the DataLayer classes)
    val name = _data.name
    val firstDosesPerc = _data.firstDosesPercentageFloat
    val fullyVaccinatedPerc = _data.fullyVaccinatedPercentageFloat
}
