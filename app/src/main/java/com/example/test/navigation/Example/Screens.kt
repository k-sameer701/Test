package com.example.test.navigation.Example

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController,) {
    val myUser = listOf(
        User(1, "1", 1, "Male"),
        User(2, "2", 2, "Female"),
        User(3, "3", 3, "Female"),
        User(4, "4", 4, "Male"),
        User(5, "5", 5, "Female"),
        User(6, "6", 6, "Male"),
    )

    LazyColumn {
        items(myUser) {
            SingleUser(navController, name = it.name, id = it.id, age = it.age, gender = it.gender)
        }
    }

}

@Composable
fun UserDetailScreen(navController: NavController, name: String, age: Int, gender: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = name)
        Text(text = age.toString())
        Text(text = gender)
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}

@Composable
fun SingleUser(navController: NavController, id: Int, name: String, age: Int, gender: String) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    NavRoutes.UserDetailScreen(
                        id = id,
                        age = age,
                        name = name,
                        gender = gender
                    )
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name)
        Text(text = age.toString())
        Text(text = gender)
    }
}