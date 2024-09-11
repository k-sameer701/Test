package com.example.test.services.backgroundServices.counterExample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterAppService: Service() {

    var counter = 0
    var job: Job? = null
    var isRunning: Boolean = false

    override fun onCreate() {
        super.onCreate()
        Log.d("MAIN", "onCreate: Service Started...")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isRunning = true

        job = CoroutineScope(Dispatchers.Default).launch {
            while (isRunning) {
                delay(1000)
                counter++
                val intent = Intent("counter_screen").apply {
                    putExtra("counter", counter)
                }
                sendBroadcast(intent)
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        isRunning = false
    }
}