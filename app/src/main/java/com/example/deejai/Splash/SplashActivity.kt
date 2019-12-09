package com.example.deejai.Splash

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deejai.R
import com.example.deejai.Splash.SplashContract.ViewModel
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import android.content.Intent
import android.util.Log
import com.example.deejai.Constants.DEVELOPER_LOG
import com.example.deejai.RoomSelection.SelectRoomActivity

class SplashActivity : AppCompatActivity(), ViewModel {

    private lateinit var presenter : SplashPresenter
    private val REDIRECT_URI = "http://example.com/callback/"
    private val CLIENT_ID = "830150d51e934ac9a91111ef638c486e"
    private val SCOPES =
        "user-read-recently-played,user-library-modify,streaming"
    private val REQUEST_CODE = 1337

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SplashPresenter(this, this)
        presenter.tryAuthenticate()
    }

    override fun onConnectionError() {
        Toast.makeText(this, getString(R.string.internet_failure), Toast.LENGTH_LONG).show()
        finish()
    }

    override fun doAuthentication(token : String) {
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

}
