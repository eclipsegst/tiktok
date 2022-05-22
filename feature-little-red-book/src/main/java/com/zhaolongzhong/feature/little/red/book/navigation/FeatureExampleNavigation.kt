package com.zhaolongzhong.feature.little.red.book.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.little.red.book.LittleRedBookApp

object FeatureLittleDestination {
    const val app = "feature-little-red-book"
}

fun NavGraphBuilder.featureLittleRedBookGraph(
    onBackClick: () -> Unit = {}
) {
    composable(route = FeatureLittleDestination.app) {
        LittleRedBookApp(onBackClick = onBackClick)
    }
}
