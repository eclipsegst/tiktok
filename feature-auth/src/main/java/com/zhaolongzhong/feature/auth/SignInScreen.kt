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
fun SignInScreen(text: String, onClick: () -> Unit = {}) {

    Column(Modifier.background(Color.LightGray)) {
        Text(
            text = "SignInScreen - $text",
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

        Button(onClick = onClick) {
            Text(text = "Sign In")
        }
    }
}

private fun sign(username: String, password: String) {
    // check if username is valid or not locally
    // check if password is valid or not locally

    // api call, return success
    // api call, waiting
}

private fun signUp(username: String, password: String) {
    // check if username is valid or not locally and remotely
    // check if password is valid or not locally

    // api call, return success
    // api call, waiting
}
