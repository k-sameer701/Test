package com.example.test.services.foregroundServices.simple

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class ForegroundServiceMainActivity: ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForegroundServiceScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForegroundServiceScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Intent(context, MyForegroundService::class.java).also { intent ->
                context.startForegroundService(intent)
            }
        }) {
            Text("Start Foreground Service")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            Intent(context, MyForegroundService::class.java).also { intent ->
                context.stopService(intent)
            }
        }) {
            Text("Stop Foreground Service")
        }
    }
}