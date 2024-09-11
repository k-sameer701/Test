package com.example.test.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class CoroutineExample : ComponentActivity() {
    //    tag:Coroutine
    private val TAG = "Coroutine"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            execute()
        }
    }

    private fun execute() {
        runBlocking {
            joinAll(
                async { CoroutineOne() } ,
                async { CoroutineTwo() }
            )
        }
    }

    suspend fun CoroutineOne() {
        Log.d(TAG, "Coroutine 1 - STARTS")
        delay(3000)
        Log.d(TAG, "Coroutine 1 - ENDS")
    }
    suspend fun CoroutineTwo() {
        Log.d(TAG, "Coroutine 2 - STARTS")
        delay(2000)
        Log.d(TAG, "Coroutine 2 - ENDS")
    }
}