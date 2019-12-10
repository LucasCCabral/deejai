package com.example.deejai.RoomSelection

import android.location.Location

class Room(
    val name : String,
    val coordinates : String,
    val picture : Int) {

    fun getDistance(): Location {
        val latitude : String? = this.coordinates.substringAfterLast(",")
        val longitude : String? = this.coordinates.substringBeforeLast(",")
        val location : Location = Location("")
        if (longitude != null) {
            location.longitude = longitude.toDouble()
        }
        if (latitude != null) {
            location.latitude = latitude.toDouble()
        }
        return location
    }
}
