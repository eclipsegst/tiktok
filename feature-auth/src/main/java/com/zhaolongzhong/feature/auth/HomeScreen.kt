package com.zhaolongzhong.feature.auth

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(text: String = "home") {
    Text(text = "Home - $text", modifier = Modifier.background(color = Color.Yellow.copy(alpha = 0.6f)))
}