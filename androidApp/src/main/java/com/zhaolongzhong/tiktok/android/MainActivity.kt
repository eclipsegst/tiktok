package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import com.zhaolongzhong.tiktok.Greeting
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Hello world! ${greet()}")
        }
    }
}
