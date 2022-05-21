package com.zhaolongzhong.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.auth.SignInView

fun NavGraphBuilder.featureAuthGraph(text: String) {
    composable(route = "feature-auth") {
        SignInView(text = text)
    }
}
