package com.example.test.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

class NewNavigationExample: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Route.ScreenA) {
                composable<Route.ScreenA> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { navController.navigate(Route.ScreenB("Raj", 23)) }) {
                            Text(text = "Go to Screen B")
                        }
                    }
                }
                composable<Route.ScreenB>{ backStackEntry ->
                    val arg = backStackEntry.toRoute<Route.ScreenB>()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "My name is ${arg.name} and I'm ${arg.age} year's old.")
                    }
                }
            }
        }
    }
}

@Serializable
sealed class Route{
    @Serializable
    data object ScreenA: Routes()

    @Serializable
    data class ScreenB(
        val name: String?,
        val age: Int
    )
}
