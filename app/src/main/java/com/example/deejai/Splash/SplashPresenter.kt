package com.example.deejai.Splash

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.deejai.Constants.SPOTIFY_CREDENTIALS
import com.example.deejai.Constants.SPOTIFY_TOKEN
import com.example.deejai.Constants.NO_TOKEN
import com.example.deejai.Splash.SplashContract.Presenter
import com.example.deejai.Splash.SplashContract.ViewModel

class SplashPresenter (private val view : ViewModel, private val context : Context) : Presenter {

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
    val isConnected : Boolean = activeNetwork?.isConnectedOrConnecting == true

    override fun tryAuthenticate() {
        if(isConnected) {
            var token = getToken()

            if(token == NO_TOKEN || token == null) {
                view.onUnauthenticated()
            } else {
                view.doAuthentication(token)
            }

        } else {
            view.onConnectionError()
        }
    }

    private fun getToken() =
        context.getSharedPreferences(SPOTIFY_CREDENTIALS, MODE_PRIVATE)
            .getString(SPOTIFY_TOKEN, NO_TOKEN)
}
