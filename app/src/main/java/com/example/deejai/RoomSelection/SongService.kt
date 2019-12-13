package com.example.deejai.RoomSelection

import Song
import android.content.Context
import android.content.SharedPreferences
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject



class SongService(applicationContext: Context) {
    private val songs: ArrayList<Song> = ArrayList()
    private var sharedPreferences: SharedPreferences? = null
    private var queue: RequestQueue? = null

    fun SongService(context: Context) {
        sharedPreferences = context.getSharedPreferences("SPOTIFY", 0)
        queue = Volley.newRequestQueue(context)
    }

    fun getSongs(): ArrayList<Song> {
        return songs
    }

    fun getRecentlyPlayedTracks(callBack: VolleyCallBack): ArrayList<Song> {
        val endpoint = "https://api.spotify.com/v1/me/player/recently-played"
        val jsonObjectRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Request.Method.GET, endpoint, null,
                Response.Listener { response: JSONObject ->
                    val gson = Gson()
                    val jsonArray = response.optJSONArray("items")
                    for (n in 0 until jsonArray.length()) {
                        try {
                            var `object` = jsonArray.getJSONObject(n)
                            `object` = `object`.optJSONObject("track")
                            val song: Song = gson.fromJson(`object`.toString(), Song::class.java)
                            songs.add(song)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    callBack.onSuccess()
                },
                Response.ErrorListener { error: VolleyError? -> }
            ) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    val token = sharedPreferences!!.getString("token", "")
                    val auth = "Bearer $token"
                    headers["Authorization"] = auth
                    return headers
                }
            }
        queue!!.add(jsonObjectRequest)
        return songs
    }

}