package com.example.test.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

class NewNavigation: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.FirstScreen, // custom type for first screen
            ) {
                composable<Routes.FirstScreen> { // custom type as generic
                    FirstScreen(onNavigateForward = {
                        // passing object for seconds class
                        navController.navigate(
                            Routes.SecondScreen(customPrimitive = "Custom primitive string")
                        )
                    })
                }
                composable<Routes.SecondScreen> {backstackEntry ->
                    // unpacking the back stack entry to obtain our value
                    val customValue = backstackEntry.toRoute<Routes.SecondScreen>()
                    Log.i("SecondScreen", customValue.customPrimitive)
                    SecondScreen(onNavigateBack = {
                        navController.navigate(
                            Routes.FirstScreen
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun FirstScreen(onNavigateForward: () -> Unit) {
    SimpleScreen(text = "First Screen", textButton = "Go forward") {
        onNavigateForward()
    }
}

@Composable
fun SecondScreen(onNavigateBack: () -> Unit) {
    SimpleScreen(text = "Second Screen", textButton = "Go back") {
        onNavigateBack()
    }
}

@Composable
fun SimpleScreen(text: String, textButton: String, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text)
        Button(onClick = onClick) {
            Text(textButton)
        }
    }
}

@Serializable
sealed class Routes{
    @Serializable
    data object FirstScreen : Routes() // pure data object without any primitive

    @Serializable
    data class SecondScreen(val customPrimitive: String) : Routes() // data class with custom primitive
}