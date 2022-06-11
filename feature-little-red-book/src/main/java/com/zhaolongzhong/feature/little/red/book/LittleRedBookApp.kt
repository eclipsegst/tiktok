package com.zhaolongzhong.feature.little.red.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhaolongzhong.feature.little.red.book.bottombar.AppBottomBar
import com.zhaolongzhong.feature.little.red.book.bottombar.TabScreen
import com.zhaolongzhong.feature.little.red.book.screens.HomeScreen

@Composable
fun LittleRedBookApp(
    onBackClick: () -> Unit = {}
) {
    Column {
        LittleRedBookAppToolbar(onBackClick = onBackClick)
        MainTabScreen()
    }
}

@Composable
private fun LittleRedBookAppToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
private fun MainTabScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
//            TopAppBar(
//                title = { Text("Little Red Book") },
//                backgroundColor = MaterialTheme.colors.surface,
//            )
        },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { AppBottomBar(navController = navController) }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {
            NavHost(navController, startDestination = TabScreen.Home.route) {
                composable(TabScreen.Home.route) {
                    HomeScreen()
                }
                composable(TabScreen.Store.route) {
                    Text(text = "Store")
                }
                composable(TabScreen.New.route) {
                    Text(text = "New")
                }
                composable(TabScreen.Messages.route) {
                    Text(text = "Messages")
                }
                composable(TabScreen.Me.route) {
                    Text(text = "Me")
                }
            }
        }
    }
}
