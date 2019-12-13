package com.example.deejai.RoomSelection

import Song
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.deejai.R


class RoomPlayer : AppCompatActivity() {
    private var userView: TextView? = null
    private var songView: TextView? = null
    private var addBtn: Button? = null
    private var song: Song? = null

    private var songService: SongService? = null
    private var recentlyPlayedTracks: ArrayList<Song>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_player)

        songService = SongService(applicationContext)
        userView = findViewById<View>(R.id.user) as TextView
        songView = findViewById<View>(R.id.song) as TextView
        addBtn = findViewById<View>(R.id.add) as Button

        val sharedPreferences = getSharedPreferences("SPOTIFY", 0)
        userView!!.setText(sharedPreferences.getString("userid", "No User"))

        getTracks()
    }

    private fun getTracks() {
        songService!!.getRecentlyPlayedTracks(object : VolleyCallBack {
            override fun onSuccess() {
                recentlyPlayedTracks = songService!!.getSongs()
                updateSong()
            }
        })
    }

    private fun updateSong() {
        if (recentlyPlayedTracks!!.size > 0) {
            songView!!.text = recentlyPlayedTracks!![0].name
            song = recentlyPlayedTracks!![0]
        }
    }
}
