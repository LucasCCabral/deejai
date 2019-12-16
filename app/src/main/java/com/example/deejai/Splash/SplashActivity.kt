package com.example.deejai.Splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.deejai.Constants.DEVELOPER_LOG
import com.example.deejai.Constants.SPOTIFY_CREDENTIALS
import com.example.deejai.Constants.USER_DATA
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomActivity
import com.example.deejai.RoomPlayer.UserService
import com.example.deejai.Callbacks.VolleyCallBack
import com.example.deejai.Splash.SplashContract.ViewModel
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse


class SplashActivity : AppCompatActivity(), ViewModel {

    private lateinit var presenter : SplashPresenter
    private val REDIRECT_URI = "http://example.com/callback/"
    private val CLIENT_ID = "830150d51e934ac9a91111ef638c486e"
    private val SCOPES =
        "user-read-recently-played,user-library-modify,streaming,user-read-playback-state, " +
                "user-modify-playback-state, playlist-modify-public, user-library-modify, " +
                "user-read-currently-playing"
    private val REQUEST_CODE = 1337
    private var queue: RequestQueue? = null
    private var msharedPreferences: SharedPreferences? = null
    private var editor: Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queue = Volley.newRequestQueue(this)
        msharedPreferences = this.getSharedPreferences(SPOTIFY_CREDENTIALS, Context.MODE_PRIVATE)
        presenter = SplashPresenter(this, this)
        presenter.tryAuthenticate()
    }

    override fun onConnectionError() {
        Toast.makeText(this, getString(R.string.internet_failure), Toast.LENGTH_LONG).show()
        finish()
    }

    override fun doAuthentication(token : String) {
        //Log.d("doAuthentication", "already auth")
        intent = Intent(this, SelectRoomActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onUnauthenticated() {
        val spotifyAuthBuilder = AuthenticationRequest.Builder(
            CLIENT_ID,
            AuthenticationResponse.Type.TOKEN,
            REDIRECT_URI
        )
        spotifyAuthBuilder.setScopes(arrayOf(SCOPES))
        val request = spotifyAuthBuilder.build()

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    Log.d(DEVELOPER_LOG, "Authentication succeeded")
                    presenter.onTokenReceived(response.accessToken)
                    waitForUserInfo()
                }

                AuthenticationResponse.Type.ERROR -> {
                    Log.d(DEVELOPER_LOG, "Authentication Error")
                    Log.d(DEVELOPER_LOG, "      response code: " + response.code)
                    Log.d(DEVELOPER_LOG, "      response error: " + response.error)
                    Toast.makeText(this, getString(R.string.api_error), Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    private fun waitForUserInfo() {
        val userService =
            UserService(queue, msharedPreferences)
        userService[object : VolleyCallBack {
            override fun onSuccess() {
                val user = userService.getUser()
                if (user != null) {
                    editor = getSharedPreferences(SPOTIFY_CREDENTIALS, Context.MODE_PRIVATE).edit().putString(USER_DATA,user.display_name)
                }
                Log.d("STARTING", "GOT USER INFORMATION")
                editor?.commit()
            }
        }]
    }

}
