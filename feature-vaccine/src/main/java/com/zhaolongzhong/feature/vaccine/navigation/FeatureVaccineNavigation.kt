package com.zhaolongzhong.feature.vaccine.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.vaccine.VaccineScreen
import com.zhaolongzhong.tiktok.viewmodel.TViewModel

object VaccineDestination {
    const val vaccineList = "vaccine-list"
    const val vaccineDetail = "vaccine-detail"
}

fun NavGraphBuilder.featureVaccineGraph(
    model: TViewModel,
    onBackClick: () -> Unit
) {
    composable(route = VaccineDestination.vaccineList) {
        VaccineScreen(onBackClick = onBackClick, model = model)
    }
}
