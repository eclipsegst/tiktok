package com.zhaolongzhong.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WelcomeScreen(
    onBackClick: () -> Unit,
    onOpenSignIn: () -> Unit,
    onOpenSignUp: () -> Unit
) {
    Column {
        // Add a tool back to navigate back for test
        AuthToolbar(onBackClick = onBackClick)
        Text(
            text = "Welcome",
            modifier = Modifier.background(color = Color.Yellow.copy(alpha = 0.6f))
        )

        Button(onClick = onOpenSignIn) {
            Text(text = "Open Sign In")
        }
        Button(onClick = onOpenSignUp) {
            Text(text = "Open Sign Up")
        }
    }
}