package com.example.deejai.RoomSelection

import User
import android.content.SharedPreferences
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deejai.Constants
import com.google.gson.Gson
import org.json.JSONObject


class UserService(queue: RequestQueue?, sharedPreferences: SharedPreferences?) {
    private val ENDPOINT = "https://api.spotify.com/v1/me"
    private var msharedPreferences: SharedPreferences? = null
    private var mqueue: RequestQueue? = null
    private var user: User? = null

    init {
        mqueue = queue
        msharedPreferences = sharedPreferences
    }

    fun getUser(): User? {
        return user
    }
    operator fun get(callBack: VolleyCallBack) {
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(ENDPOINT, null,
            Response.Listener { response: JSONObject ->
                val gson = Gson()
                user = gson.fromJson(response.toString(), User::class.java)
                callBack.onSuccess()
            },
            Response.ErrorListener { error: VolleyError? -> get(object : VolleyCallBack {
                override fun onSuccess() {

                }
            }) }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                val token = msharedPreferences!!.getString(Constants.SPOTIFY_TOKEN,
                    Constants.NO_TOKEN
                )
                val auth = "Bearer $token"
                headers["Authorization"] = auth
                return headers
            }
        }
        mqueue!!.add(jsonObjectRequest)
    }

}