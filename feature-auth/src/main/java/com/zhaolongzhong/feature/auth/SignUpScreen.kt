package com.zhaolongzhong.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SignUpScreen(onBackClick: () -> Unit = {}, onSend: () -> Unit = {}) {

    Column(Modifier.background(Color.LightGray)) {
        AuthToolbar(onBackClick = onBackClick)
        Text(
            text = "SignUpScreen",
            modifier = Modifier.background(color = Color.Yellow.copy(alpha = 0.6f))
        )

        TextField(value = "",
            onValueChange = {},
            label = { Text(text = "Username / Email") },
            placeholder = { Text(text = "Username / Email Hint") }
        )

        TextField(value = "",
            onValueChange = {},
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password Hint") }
        )

        Button(onClick = onSend) {
            Text(text = "Sign Up")
        }
    }
}
