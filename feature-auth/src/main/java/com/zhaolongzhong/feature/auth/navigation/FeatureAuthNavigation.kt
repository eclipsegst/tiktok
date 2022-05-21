package com.zhaolongzhong.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.auth.HomeScreen
import com.zhaolongzhong.feature.auth.SignInScreen
import com.zhaolongzhong.feature.auth.WelcomeScreen

fun NavGraphBuilder.featureAuthGraph(text: String, onOpenSignIn: () -> Unit, onSignIn: () -> Unit) {
    composable(route = "feature-welcome") {
        WelcomeScreen(text = text, onClick = onOpenSignIn)
    }
    composable(route = "feature-signin") {
        SignInScreen(text = text, onClick = onSignIn)
    }
    composable(route = "feature-home") {
        HomeScreen(text = text)
    }
}
