package com.zhaolongzhong.tiktok.android

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhaolongzhong.tiktok.viewmodel.screens.CountriesListItem
import com.zhaolongzhong.tiktok.viewmodel.screens.CountriesListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountriesListScreen(
    countriesListState: CountriesListState,
    onListItemClick: (String) -> Unit,
    onFavoriteIconClick: (String) -> Unit,
) {
    if (countriesListState.isLoading) {
        LoadingScreen()
    } else {
        if (countriesListState.countriesListItems.isEmpty()) {
            Text(
                text = "empty list",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        } else {
            LazyColumn {
                stickyHeader {
                    CountriesListHeader()
                }
                items(items = countriesListState.countriesListItems, itemContent = { item ->
                    CountriesListRow(
                        item = item,
                        favorite = true,
                        onItemClick = { onListItemClick(item.name) },
                        onFavoriteIconClick = { onFavoriteIconClick(item.name) })
                })
            }
        }
    }
}

@Composable
fun CountriesListHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray)
            .padding(start = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "country", fontSize = 16.sp)
        }
        Column(modifier = Modifier.width(70.dp), horizontalAlignment = Alignment.End) {
            Text(text = "first\ndose", fontSize = 15.sp, textAlign = TextAlign.Right)
        }
        Column(modifier = Modifier.width(70.dp), horizontalAlignment = Alignment.End) {
            Text(text = "fully\nvax'd", fontSize = 15.sp, textAlign = TextAlign.Right)
        }
        Column(
            modifier = Modifier.width(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "favorite?", fontSize = 15.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun CountriesListRow(
    item: CountriesListItem,
    favorite: Boolean,
    onItemClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .height(50.dp)
            .padding(start = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = Modifier.width(70.dp), horizontalAlignment = Alignment.End) {
            Text(text = item.firstDosesPerc.toString(), style = MaterialTheme.typography.body1)
        }
        Column(modifier = Modifier.width(70.dp), horizontalAlignment = Alignment.End) {
            Text(text = item.fullyVaccinatedPerc.toString(), style = MaterialTheme.typography.body1)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .clickable(onClick = onFavoriteIconClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (favorite) {
                Icon(Icons.Default.Star, contentDescription = "favorite", tint = Color.Magenta)
            } else {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "not a favorite",
                    tint = Color.LightGray
                )
            }
        }
    }
    Divider()
}
