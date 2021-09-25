package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.zhaolongzhong.tiktok.datalayer.functions.getCountriesListData
import com.zhaolongzhong.tiktok.viewmodel.screens.CountriesListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private var screenState =
        mutableStateOf(CountriesListState(isLoading = true, countriesListItems = emptyList()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = (application as TApp).model

        Log.d(TAG, "setContent")

        setContent {
            CountriesListScreen(
                countriesListState = screenState.value,
                onListItemClick = {
                    Log.d(TAG, "onListItemClick: $it")
                },
                onFavoriteIconClick = { Log.d(TAG, "onFavoriteIconClick: $it") },
            )
        }

        // TODO: handle coroutine scope properly
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val result = model.repo.getCountriesListData()
            Log.d(TAG, "Update state with list size: ${result.size}")
            screenState.value = CountriesListState(isLoading = false, countriesListItems = result)
        }
    }
}
