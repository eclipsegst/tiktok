package com.zhaolongzhong.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.auth.SignInView

object FeatureAuthDestination {
    const val welcome = "welcome"
}

fun NavGraphBuilder.featureAuthGraph(onBackClick: () -> Unit) {
    composable(route = FeatureAuthDestination.welcome) {
        SignInView(onBackClick = onBackClick)
    }
}
