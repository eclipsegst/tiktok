package com.zhaolongzhong.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zhaolongzhong.feature.auth.HomeScreen
import com.zhaolongzhong.feature.auth.SignInScreen
import com.zhaolongzhong.feature.auth.SignUpScreen
import com.zhaolongzhong.feature.auth.WelcomeScreen

object FeatureAuthDestination {
    const val welcome = "welcome"
    const val signIn = "signIn"
    const val signUp = "signUp"
    const val home = "home"
}

fun NavGraphBuilder.featureAuthGraph(
    navController: NavHostController,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
) {
    composable(route = FeatureAuthDestination.welcome) {
        WelcomeScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onOpenSignIn = {
                navController.navigate(FeatureAuthDestination.signIn)
            },
            onOpenSignUp = {
                navController.navigate(FeatureAuthDestination.signUp)
            }
        )
    }
    composable(route = FeatureAuthDestination.signIn) {
        SignInScreen(onBackClick = {
            navController.popBackStack()
        }, onSignIn = onSignIn)
    }
    composable(route = FeatureAuthDestination.signUp) {
        SignUpScreen(onBackClick = {
            navController.popBackStack()
        }, onSend = onSignUp)
    }
    composable(route = FeatureAuthDestination.home) {
        HomeScreen() {
            navController.popBackStack()
        }
    }
}
