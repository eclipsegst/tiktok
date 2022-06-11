package com.zhaolongzhong.feature.little.red.book.bottombar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomBar(navController: NavHostController) {
    val tabs =
        listOf(TabScreen.Home, TabScreen.Store, TabScreen.New, TabScreen.Messages, TabScreen.Me)
    BottomNavigation() {
        val backstackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = TabScreen.fromRoute(backstackEntry?.destination?.route)
        tabs.forEach { screen ->
            var multiplier by remember { mutableStateOf(1f) }
            BottomNavigationItem(
                icon = { Icon(screen.icon, null) },
                label = {
                    Text(text = screen.title,
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        style = LocalTextStyle.current.copy(
                            fontSize = LocalTextStyle.current.fontSize * multiplier
                        ),
                        onTextLayout = {
                            if (it.hasVisualOverflow) {
                                multiplier *= 0.99f
                            }
                        })
                },
                alwaysShowLabel = screen != TabScreen.New,
                selected = currentScreen.route == screen.route,
                onClick = {
                    if (currentScreen.route == screen.route) {
                        return@BottomNavigationItem
                    }

                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
