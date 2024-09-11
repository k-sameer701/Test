package com.example.test.navigation.Example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

class NavigationExample: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = NavRoutes.ListScreen) {
                composable<NavRoutes.ListScreen> {
                    ListScreen(navController)
                }
                composable<NavRoutes.UserDetailScreen> { navBackStackEntry ->
                    val args = navBackStackEntry.toRoute<NavRoutes.UserDetailScreen>()
                    UserDetailScreen(navController, args.name, args.age, args.gender)
                }
            }
        }
    }
}