package com.zhaolongzhong.tiktok.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhaolongzhong.feature.auth.navigation.FeatureAuthDestination
import com.zhaolongzhong.feature.auth.navigation.featureAuthGraph
import com.zhaolongzhong.feature.example.navigation.FeatureExampleDestination
import com.zhaolongzhong.feature.example.navigation.featureExampleGraph
import com.zhaolongzhong.feature.vaccine.navigation.VaccineDestination
import com.zhaolongzhong.feature.vaccine.navigation.featureVaccineGraph
import com.zhaolongzhong.tiktok.viewmodel.TViewModel

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = (application as TApp).model

        setContent {
            MainComposable(model = model)
        }
    }
}

@Composable
fun MainComposable(model: TViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "playground",
        modifier = Modifier
    ) {
        playgroundGraph { route ->
            navController.navigate(route = route)
        }
        featureExampleGraph(text = "MainComposable") {
            navController.popBackStack()
        }
        featureVaccineGraph(model = model) {
            navController.popBackStack()
        }
        featureAuthGraph {
            navController.popBackStack()
        }
    }
}
fun NavGraphBuilder.playgroundGraph(onClick: (String) -> Unit) {
    composable(route = "playground") {
        Playground(onClick = onClick)
    }
}

@Composable
fun Playground(onClick: (String) -> Unit) {
    Column() {
        Button(onClick = { onClick(FeatureExampleDestination.featureExample)}) {
            Text(text = "Feature Example")
        }
        Button(onClick = { onClick(VaccineDestination.vaccineList)}) {
            Text(text = "Feature Covid Vaccine")
        }
        Button(onClick = { onClick(FeatureAuthDestination.welcome)}) {
            Text(text = "Feature Auth")
        }
    }
}
