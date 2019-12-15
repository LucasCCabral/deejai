package com.example.deejai.RoomSelection

import Song
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.Constants
import com.example.deejai.Constants.USER_DATA
import com.example.deejai.R
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector.ConnectionListener
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.PlayerState
import com.spotify.protocol.types.Track
import kotlinx.android.synthetic.main.activity_room_player.*


class RoomPlayer : AppCompatActivity() {
    private var userView: TextView? = null
    private val REDIRECT_URI = "http://example.com/callback/"
    private val CLIENT_ID = "830150d51e934ac9a91111ef638c486e"
    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    private var songService: SongService? = null
    private var recentlyPlayedTracks: ArrayList<Song>? = null

    var modelArrayList: ArrayList<Song> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_player)

        songService = SongService(applicationContext)
        userView = findViewById<View>(R.id.user) as TextView
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(applicationContext)
        recyclerview.layoutManager = mLayoutManager

        val sharedPreferences = getSharedPreferences(Constants.SPOTIFY_CREDENTIALS, Context.MODE_PRIVATE)
        //val userToken = sharedPreferences.getString(Constants.SPOTIFY_TOKEN, Constants.NO_TOKEN)
        userView!!.setText(sharedPreferences.getString(USER_DATA, "No User"))

        getTracks()
    }

    override fun onStart() {
        super.onStart()

        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams,
            object : ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    mSpotifyAppRemote = spotifyAppRemote
                    Log.d("RoomPlayer", "Connected! Yay!")
                    // Now you can start interacting with App Remote
                    connected()
                }

                override fun onFailure(throwable: Throwable) {
                    Log.e("RoomPlayer", throwable.message, throwable)
                    // Something went wrong when attempting to connect! Handle errors here
                }
            })

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
        for (i in 0 until recentlyPlayedTracks!!.size) {
            val song = Song("", "")
            song.name = recentlyPlayedTracks!![i].name
            song.id = recentlyPlayedTracks!![i].id
            modelArrayList.add(song)
        }
        /*
        if (recentlyPlayedTracks!!.size > 0) {
            song.text = recentlyPlayedTracks!![0].name
            song2.text = recentlyPlayedTracks!![1].name
            song3.text = recentlyPlayedTracks!![2].name
        }*/

        val adapter = SongAdapter(modelArrayList, applicationContext)

        recyclerview.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    private fun connected() { // Play a playlist
        mSpotifyAppRemote!!.playerApi.play("spotify:playlist:37i9dQZF1EtmslFZGwD6iR")
        // Subscribe to PlayerState
        mSpotifyAppRemote!!.playerApi
            .subscribeToPlayerState()
            .setEventCallback { playerState: PlayerState ->
                val track: Track? = playerState.track
                if (track != null) {
                    Log.d(
                        "RoomPlayer",
                        track.name.toString() + " by " + track.artist.name
                    )
                }
            }
    }
}
