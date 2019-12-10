package com.example.deejai.RoomSelection

import android.location.Location
import com.example.deejai.Constants.NO_COORDINATES

class Room(
    val name : String,
    val description : String,
    val coordinates : String,
    val picture : Int ) {

    val COORDINATES_DELIMITER = ","
    val METER_TO_KILOMETER = 1000

    fun getDistance(userCoordinates : String?): String {
        val roomLocation = getLocation(getLat(coordinates),
            getLon(coordinates)
        )
        var userLocation : Location
        if(userCoordinates == NO_COORDINATES || userCoordinates == null) {
            userLocation = roomLocation

        } else {
            userLocation = getLocation(getLat(userCoordinates),
                getLon(userCoordinates)
            )
        }
        return String.format("%.1f Km", userLocation.distanceTo(roomLocation)/METER_TO_KILOMETER)
    }

    fun getLat(coordinates : String) = coordinates.substringAfterLast(COORDINATES_DELIMITER)

    fun getLon(coordinates : String) = coordinates.substringBefore(COORDINATES_DELIMITER)

    fun getLocation(latitude : String, longitude : String) : Location{
        val location = Location("")
        location.longitude = longitude.toDouble()
        location.latitude = latitude.toDouble()
        return location
    }
}
