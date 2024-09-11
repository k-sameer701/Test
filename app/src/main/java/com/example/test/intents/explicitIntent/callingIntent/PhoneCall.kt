package com.example.test.intents.explicitIntent.callingIntent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class PhoneCall: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val callIntent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel: +91 8920136865")
                    )
                    val webIntent: Intent = Uri.parse("https://www.android.com").let { webpage ->
                        Intent(Intent.ACTION_VIEW, webpage)
                    }
                    // Map point based on address
                    val mapIntent: Intent = Uri.parse(
                        "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
                    ).let { location ->
                        // Or map point based on latitude/longitude
                        // val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14") // z param is zoom level
                        Intent(Intent.ACTION_VIEW, location)
                    }
                    Button(onClick = {
                        startActivity(callIntent)
                    }) {
                        Text(text = "Call Him")
                    }
                    Button(onClick = {
                        startActivity(webIntent)
                    }) {
                        Text(text = "Web Intent")
                    }
                    Button(onClick = {
                        startActivity(mapIntent)
                    }) {
                        Text(text = "Map")
                    }
                }
            }
        }
    }
}