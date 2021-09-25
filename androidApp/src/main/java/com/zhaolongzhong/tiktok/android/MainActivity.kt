package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zhaolongzhong.tiktok.viewmodel.Screen
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

    when (navigation.screenState.collectAsState().value) {
        Screen.List -> {
            CountriesListScreen(
                countriesListState = model.stateManager.countryListScreenState.collectAsState().value,
                onListItemClick = {
                    navigation.navigate(Screen.Detail)
                },
                onFavoriteIconClick = { },
            )
        }
        Screen.Detail -> {
            CountryDetailScreen(
                countryDetailState = model.stateManager.detailState.collectAsState().value,
                onDismiss = {
                    navigation.navigate(Screen.List)
                })
        }
    }
}
