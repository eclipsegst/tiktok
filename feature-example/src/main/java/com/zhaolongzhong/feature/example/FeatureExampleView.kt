package com.zhaolongzhong.feature.example

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FeatureExampleView(text: String) {
    Text(text = "Feature example module - $text", modifier = Modifier.background(color = Color.Yellow.copy(alpha = 0.6f)))
}