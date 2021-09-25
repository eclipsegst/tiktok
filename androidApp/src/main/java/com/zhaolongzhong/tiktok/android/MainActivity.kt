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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zhaolongzhong.tiktok.datalayer.Repository
import com.zhaolongzhong.tiktok.datalayer.functions.getCountriesListData
import com.zhaolongzhong.tiktok.viewmodel.TViewModel
import com.zhaolongzhong.tiktok.viewmodel.screens.CountriesListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
    val appState = AppState(model.repo)

    val appStateFlow = remember { mutableStateOf(appState) }
    val stateFlow = remember { mutableStateOf(appState) }
    val screenStateflow = remember { mutableStateOf(appState.screenState) }

    when (screenStateflow.value.value) {
        Screen.List -> {
            CountriesListScreen(
                countriesListState = stateFlow.value.countryListScreenState.value,
                onListItemClick = {
                    appStateFlow.value.navigate(Screen.Detail)
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
                        appStateFlow.value.navigate(Screen.List)
                    }
            )
        }
    }
}

enum class Screen(
    val id: String,
) {
    List("list"),
    Detail("detail")
}

class AppState(private val repo: Repository) {

    val screenState = mutableStateOf(Screen.List)

    var countryListScreenState =
        mutableStateOf(CountriesListState(isLoading = true, countriesListItems = emptyList()))

    init {
        // TODO: handle coroutine scope properly
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val result = repo.getCountriesListData()
            countryListScreenState.value =
                CountriesListState(isLoading = false, countriesListItems = result)
        }
    }

    fun navigate(screen: Screen) {
        screenState.value = screen
    }
}
