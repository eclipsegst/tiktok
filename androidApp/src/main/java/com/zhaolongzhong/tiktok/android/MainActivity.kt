package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.zhaolongzhong.tiktok.Greeting
import com.zhaolongzhong.tiktok.datalayer.functions.getCountriesListData
import com.zhaolongzhong.tiktok.viewmodel.debugLogger
import kotlinx.coroutines.runBlocking

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = (application as TApp).model

        setContent {
            Text("Hello world! ${greet()}")
        }

        runBlocking {
            model.repo.webservices.getExampleResponse()
        }

        runBlocking {
            val result = model.repo.getCountriesListData()
            debugLogger.log("$result")
        }
    }
}
