package com.zhaolongzhong.feature.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.example.FeatureExampleView

object FeatureExampleDestination {
    const val featureExample = "feature-example"
}

fun NavGraphBuilder.featureExampleGraph(
    text: String,
    onBackClick: () -> Unit = {}
) {
    composable(route = FeatureExampleDestination.featureExample) {
        FeatureExampleView(text = text, onBackClick = onBackClick)
    }
}
