package com.example.test.navigation.Example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController) {
    val myUser = listOf(
        User(1, "Naruto", 14, "Male"),
        User(2, "TenTen", 15, "Female"),
        User(3, "Hinata", 14, "Female"),
        User(4, "Shikamaru", 15, "Male"),
        User(5, "Sakura", 15, "Female"),
        User(6, "Kakashi", 23, "Male")
    )

    LazyColumn(
        modifier = Modifier.padding(15.dp)
    ) {
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
        Text(text = name, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = age.toString(), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = gender, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
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
            .padding(5.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.LightGray)
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
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 15.sp)
        Text(text = age.toString(), fontSize = 15.sp)
        Text(text = gender, fontSize = 15.sp)
    }
}