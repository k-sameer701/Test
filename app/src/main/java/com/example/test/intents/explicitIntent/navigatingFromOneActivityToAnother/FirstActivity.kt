package com.example.test.intents.explicitIntent.navigatingFromOneActivityToAnother

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.intents.explicitIntent.callingIntent.PhoneCall
import com.example.test.ui.theme.TestTheme

class FirstActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "This is the First Activity")
                        Spacer(modifier = Modifier.height(25.dp))
                        Button(
                            onClick = {
                                Intent(applicationContext, PhoneCall::class.java).also {
                                    startActivity(it)
                                }
                            }
                        ) {
                            Text(text = "Call Him")
                        }
                    }
                }
            }
        }
    }
}