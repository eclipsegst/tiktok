package com.zhaolongzhong.feature.little.red.book.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabScreen (val route: String) {
    object Home : TabScreen("home")
    object Store : TabScreen("store")
    object New : TabScreen("new")
    object Messages : TabScreen("messages")
    object Me : TabScreen("me")

    companion object {
        fun fromRoute(route: String?): TabScreen =
            when (route) {
                Home.route -> Home
                Store.route -> Store
                New.route -> New
                Messages.route -> Messages
                Me.route -> Me
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

    val icon: ImageVector
        get() = when (this) {
            Home -> Icons.Outlined.Home
            Store -> Icons.Outlined.ShoppingCart
            New -> Icons.Outlined.Add
            Messages -> Icons.Outlined.Send
            Me -> Icons.Outlined.Person
        }

    val title: String
        get() = when (this) {
            Home -> "Home"
            Store -> "Store"
            New -> ""
            Messages -> "Messages"
            Me -> "Me"
        }
}
