package com.example.deejai.RoomSelection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.deejai.Constants.ROOM_NAME_KEY
import com.example.deejai.R

class RoomPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_player)
        var bundle :Bundle? = intent.extras
        var message = bundle!!.getString(ROOM_NAME_KEY)
        val mytext : TextView = findViewById(R.id.room_name)
        mytext.text = message
    }
}
