package com.example.deejai.Splash

interface SplashContract {

    interface ViewModel {
        fun onConnectionError()
        fun onUnauthenticated()
        fun doAuthentication(token : String)
    }

    interface Presenter {
        fun tryAuthenticate()
        fun onTokenReceived(token : String)
    }

}
