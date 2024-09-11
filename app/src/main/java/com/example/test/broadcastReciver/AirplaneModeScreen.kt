package com.example.test.broadcastReciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

// EXAMPLE 1
@Composable
fun AirPlaneModeScreen(){
    var data by remember { mutableStateOf("") }
    var context = LocalContext.current
    var broadcastReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val bundle = intent?.getBooleanExtra("state", false) ?: return
                data = if (bundle) {
                    "Air Plane Mode Enable"
                } else {
                    "Air Plane Mode Disable"
                }
            }
        }
    }

    DisposableEffect(key1 = true) {
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).apply {
            context.registerReceiver(broadcastReceiver, this)
        }
        onDispose { context.unregisterReceiver(broadcastReceiver) }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = data)
    }
}
