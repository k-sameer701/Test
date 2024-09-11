package com.example.test.sideEffectsInCompose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LearnLaunchedComposable() {
    WithLaunchedEffect()
}

@Composable
fun WithLaunchedEffect() {

    var counter by remember { mutableStateOf(0) }
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        // on every recomposition , this toast will show
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Click here")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "$counter")
    }

}

@Composable
fun CounterExample1() {
    var count by remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = true) {
        delay(2000)
        Log.d("LAUNCHED_EFFECT", "Current Count 1: $count")
    }
    Column {
        Log.d("LAUNCHED_EFFECT", "Current Count 2: $count")
        Button(onClick = {count += 1}) {
            Log.d("LAUNCHED_EFFECT", "Current Count 3: $count")
            Text(text = "Increase")
        }
        Log.d("LAUNCHED_EFFECT", "Current Count 4: $count")
        Text(text = "$count")
    }
}

@Composable
fun CounterExample2() {
    var count by remember { mutableIntStateOf(0) }
    var key = count % 3 == 0
    LaunchedEffect(key1 = key) {
        delay(2000)
        Log.d("LAUNCHED_EFFECT", "Current Count 1: $count")
    }
    Column {
        Log.d("LAUNCHED_EFFECT", "Current Count 2: $count")
        Button(onClick = {count += 1}) {
            Log.d("LAUNCHED_EFFECT", "Current Count 3: $count")
            Text(text = "Increase")
        }
        Log.d("LAUNCHED_EFFECT", "Current Count 4: $count")
        Text(text = "$count")
    }
}