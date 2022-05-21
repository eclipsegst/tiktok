package com.zhaolongzhong.feature.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FeatureExampleView(
    text: String,
    onBackClick: () -> Unit = {}
) {
    Column {
        ExampleToolbar(onBackClick = onBackClick)
        Text(
            text = "Feature example module - $text",
            modifier = Modifier.background(color = Color.Yellow.copy(alpha = 0.6f))
        )
    }
}

@Composable
private fun ExampleToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
