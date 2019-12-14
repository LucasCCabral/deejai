package com.example.deejai.RoomSelection

import Song
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.deejai.Constants
import com.example.deejai.R
import kotlinx.android.synthetic.main.activity_room_player.*


class RoomPlayer : AppCompatActivity() {
    private var userView: TextView? = null
    private var songView: TextView? = null
    private var addBtn: Button? = null

    private var songService: SongService? = null
    private var recentlyPlayedTracks: ArrayList<Song>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_player)

        songService = SongService(applicationContext)
        userView = findViewById<View>(R.id.user) as TextView


        val sharedPreferences = getSharedPreferences(Constants.SPOTIFY_CREDENTIALS, Context.MODE_PRIVATE)
        //val userToken = sharedPreferences.getString(Constants.SPOTIFY_TOKEN, Constants.NO_TOKEN)
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
            song.text = recentlyPlayedTracks!![0].name
            song2.text = recentlyPlayedTracks!![1].name
            song3.text = recentlyPlayedTracks!![2].name
        }
    }
}
