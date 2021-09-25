package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val stateFlow = remember { mutableStateOf(appState) }

    CountriesListScreen(
        countriesListState = stateFlow.value.countryListScreenState.value,
        onListItemClick = { },
        onFavoriteIconClick = { },
    )
}

class AppState(private val repo: Repository) {

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
}
