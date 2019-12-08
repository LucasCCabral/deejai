package com.example.deejai.Splash

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deejai.R
import com.example.deejai.Splash.SplashContract.ViewModel

class SplashActivity : AppCompatActivity(), ViewModel {

    private lateinit var presenter : SplashPresenter

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
    }

    override fun onUnauthenticated() {
    }

}
