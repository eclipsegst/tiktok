package com.zhaolongzhong.feature.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.example.FeatureExampleView

fun NavGraphBuilder.featureExampleGraph(text: String) {
    composable(route = "feature-example") {
        FeatureExampleView(text = text)
    }
}
