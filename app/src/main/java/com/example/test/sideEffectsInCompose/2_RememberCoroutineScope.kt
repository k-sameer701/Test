package com.example.test.sideEffectsInCompose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopeComposable() {
    WithRememberCoroutineScope()
}

@Composable
fun WithRememberCoroutineScope() {
    val coroutineScope = rememberCoroutineScope()
    val data = remember { mutableStateOf("") }

    Button(onClick = {
        coroutineScope.launch {
            // Simulate network call
            delay(2000)
            data.value = "Data loaded"
        }
    }) {
        Text("Load data")
    }

    Text(text = data.value)
}

@Composable
fun Example1() {
    var counter by remember { mutableIntStateOf(0) }
    var scope = rememberCoroutineScope()

    var text = "Counter is running $counter"
    if (counter == 5) {
        text = "Counter Stopped"
        Log.d("REMEMBER_COROUTINE_SCOPE", "STOPPED")
    }
    Column {
        Log.d("REMEMBER_COROUTINE_SCOPE", text)
        Text(text = text)
        Log.d("REMEMBER_COROUTINE_SCOPE", text)
        Button(
            onClick = {
                scope.launch {
                    Log.d("REMEMBER_COROUTINE_SCOPE", "Started...")
                    try {
                        for (i in 1..5) {
                            counter++
                            delay(1000)
                        }
                    }
                    catch (e: Exception) {
                        Log.d("REMEMBER_COROUTINE_SCOPE", "Exception Occur")
                    }
                }
            }
        ) {
            Log.d("REMEMBER_COROUTINE_SCOPE", "Button CLICK")
            Text(text = "START")
        }
    }
}