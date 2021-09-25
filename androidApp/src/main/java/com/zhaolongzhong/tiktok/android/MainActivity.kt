package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            Text(
                text = "Detail page",
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Yellow)
                    .clickable {
                        navigation.navigate(Screen.List)
                    }
            )
        }
    }
}
