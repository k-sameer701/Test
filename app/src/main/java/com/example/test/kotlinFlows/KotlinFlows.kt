package com.example.test.kotlinFlows

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class KotlinFlows: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            execute()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun execute() {
        GlobalScope.launch(Dispatchers.Main) {
            val consumer: Flow<Int> = producer()
            consumer
                // 🍎 collect -> Collect each emission of the flow.
                .collect { flowCollect ->
                    Log.d("CONSUMER ", "Item - $flowCollect")
                }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun producer(): Flow<Int> {
    val mutableStateFlow = MutableStateFlow<Int>(10)
    GlobalScope.launch {
        delay(1500)
        mutableStateFlow.emit(20)
        delay(1500)
        mutableStateFlow.emit(30)
    }
    return mutableStateFlow
}


