package com.zhaolongzhong.feature.little.red.book

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NearbyScreen() {
    Text(
        text = "Page - Nearby", modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}
