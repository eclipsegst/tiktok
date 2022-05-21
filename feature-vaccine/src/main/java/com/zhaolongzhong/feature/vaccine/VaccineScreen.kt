package com.zhaolongzhong.feature.vaccine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zhaolongzhong.tiktok.viewmodel.Screen
import com.zhaolongzhong.tiktok.viewmodel.ScreenIdentifier
import com.zhaolongzhong.tiktok.viewmodel.TViewModel

@Composable
fun VaccineScreen(
    onBackClick: () -> Unit,
    model: TViewModel
) {
    val navigation = model.navigation
    Column {
        CovidVaccineToolbar(onBackClick = onBackClick)

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
    }
}

@Composable
private fun CovidVaccineToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
