package com.zhaolongzhong.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WelcomeScreen(text: String = "Welcome", onClick: () -> Unit) {
    Column {
        Text(
            text = "Welcome - $text",
            modifier = Modifier.background(color = Color.Yellow.copy(alpha = 0.6f))
        )

        Button(onClick = onClick) {
            Text(text = "Open Sign In")
        }
    }
}