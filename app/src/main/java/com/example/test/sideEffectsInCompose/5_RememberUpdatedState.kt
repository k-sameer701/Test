package com.example.test.sideEffectsInCompose

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun RememberUpdatedComposable() {
    ParentComponent()
}

@Composable
fun ParentComponent() {
    Log.d("RUS", "1")
    var dynamicData by remember { mutableStateOf("") }
    Log.d("RUS", "2")
    LaunchedEffect(Unit) {
        delay(3000L)
        dynamicData = "New Text"
        Log.d("RUS", "3")
    }
    Log.d("RUS", "4")
    MyComponent(title = dynamicData)
    Log.d("RUS", "5")
}

@Composable
fun MyComponent(title: String) {
    Log.d("RUS", "6")
    var data by remember { mutableStateOf("") }
    Log.d("RUS", "7")
    val updatedData by rememberUpdatedState(title)
    Log.d("RUS", "8")
    LaunchedEffect(Unit) {
        delay(5000L)
        data = updatedData
        Log.d("RUS", "9")
    }
    Log.d("RUS", "10")
    Text(text = data)
    Log.d("RUS", "11")
}