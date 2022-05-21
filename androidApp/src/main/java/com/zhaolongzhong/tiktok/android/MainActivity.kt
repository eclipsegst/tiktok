package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zhaolongzhong.feature.auth.navigation.featureAuthGraph
import com.zhaolongzhong.feature.example.navigation.featureExampleGraph
import com.zhaolongzhong.tiktok.viewmodel.Screen
import com.zhaolongzhong.tiktok.viewmodel.ScreenIdentifier
import com.zhaolongzhong.tiktok.viewmodel.TViewModel

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = (application as TApp).model

        setContent {
            MainComposable(model = model)
        }
    }
}

@Composable
fun MainComposable(model: TViewModel) {
    val navigation = model.navigation

    when (navigation.screenState.collectAsState().value.screen) {
        Screen.List -> {
            CountriesListScreen(
                countriesListState = model.stateManager.countryListScreenState.collectAsState().value,
                onListItemClick = {
                    navigation.navigate(ScreenIdentifier(screen = Screen.Detail, params = it))
                },
                onFavoriteIconClick = { },
            )
        }
        Screen.Detail -> {
            CountryDetailScreen(
                countryDetailState = model.stateManager.detailState.collectAsState().value,
                onDismiss = {
                    navigation.navigate(ScreenIdentifier(screen = Screen.List))
                }
            )
        }
    }

    NavHost(
        navController = rememberNavController(),
        startDestination = "feature-auth",
        modifier = Modifier.size(width = 300.dp, height = 100.dp)
    ) {
        featureExampleGraph(text = "MainComposable")
        featureAuthGraph(text = "MainComposable")
    }
}
