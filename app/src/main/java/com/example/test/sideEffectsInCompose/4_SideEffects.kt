package com.example.test.sideEffectsInCompose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun SideEffectComposable() {
    WithSideEffect()
}

@Composable
fun WithSideEffect() {
    // Define a state variable for the count
    val count = remember { mutableStateOf(0) }

    Log.d("SIDE-EFFECT","1")

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        Log.d("SIDE-EFFECT","Outer Count is ${count.value}")
    }

    Log.d("SIDE-EFFECT","2")
    Column {
        Button(onClick = { count.value++ }) {
            // Use SideEffect to log the current value of count
            SideEffect {
                // Called on every recomposition
                Log.d("SIDE-EFFECT","Inner Count is ${count.value}")
            }

            // This recomposition doesn't trigger the outer side effect
            // every time button has been tapped
            Text("Increase Count ${count.value}")
            Log.d("SIDE-EFFECT","3")
        }
    }
    Log.d("SIDE-EFFECT","4")
}

@Composable
fun WithOutSideEffectExample() {
    val count = remember { mutableStateOf(0) }

    Log.d("SIDE-EFFECT", "Count is ${count.value}")

    Button(onClick = { count.value++ }) {
        Text(text = "Click here!")
    }
}