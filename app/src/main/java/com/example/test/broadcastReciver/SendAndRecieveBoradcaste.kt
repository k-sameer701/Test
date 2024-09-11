package com.example.test.broadcastReciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp

// EXAMPLE 2
@Composable
fun SendAndReceiveBroadcast() {
    var data by remember { mutableStateOf("") }
    val context = LocalContext.current
    val broadcastReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                data = intent?.getStringExtra("data") ?: "No Data"
            }

        }
    }
    DisposableEffect(key1 = true) {
        IntentFilter("event").apply {
            context.registerReceiver(broadcastReceiver, this)
        }
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    Column {
        Text(text = "No Data")
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            val intent = Intent("event").apply {
                putExtra("data", "Data from Broadcast")
            }
            context.sendBroadcast(intent)
        }) {
            Text(text = "Send Broadcast Data")
        }
    }
}