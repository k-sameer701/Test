package com.example.test.services.backgroundServices.gfgExample

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    // declaring object of MediaPlayer
    // private lateinit var player: MediaPlayer

    private var count: Int = 0

    // execution of service will start
    // on calling this method
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        // player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)

        // providing the boolean
        // value as true to play
        // the audio on loop
        // player.setLooping(true)

        count += 1

        // starting the process
//        player.start()

        // returns the status
        // of the program
        return START_STICKY
    }

    // execution of the service will
    // stop on calling this method
    override fun onDestroy() {
        count = 0
        super.onDestroy()
        // stopping the process
//        player.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}