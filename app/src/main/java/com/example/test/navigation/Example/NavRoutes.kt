package com.example.test.navigation.Example

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {
    @Serializable
    data object ListScreen: NavRoutes()

    @Serializable
    data class UserDetailScreen(
        val id: Int,
        val name: String,
        val age: Int,
        val gender: String
    ): NavRoutes()
}