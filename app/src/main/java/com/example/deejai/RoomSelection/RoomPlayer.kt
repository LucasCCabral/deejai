package com.example.deejai.RoomSelection

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.example.deejai.R
import kotlinx.android.synthetic.main.activity_room_player.*


class RoomPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_player)
        webview.getSettings().setJavaScriptEnabled(true)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val html = "<iframe src=\"https://open.spotify.com/embed/album/1DFixLWuPkv3KT3TnV35m3\" width=\"350\" height=\"530\" frameborder=\"0\" allowtransparency=\"true\" allow=\"encrypted-media\"></iframe>"
        webview.loadData(html, "text/html", null)
    }
}
