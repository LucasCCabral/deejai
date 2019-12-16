package com.example.deejai.RoomSelection

import android.content.Context
import android.location.Location
import com.example.deejai.Constants.USER_DATA
import com.example.deejai.Constants.USER_DISTANCE
import com.example.deejai.Data.Room
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.Presenter
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel
import com.google.android.gms.location.LocationServices

class SelectRoomPresenter(private val view: ViewModel,
                          private val context : Context) : Presenter {
    var mockRooms = ArrayList<Room>()

    override fun listAvaiableRooms() {
        view.showAvaiableRooms(mockRooms)
    }

    override fun addNewRoom(newRoomName: String) {
        mockRooms.add(
            Room(
                newRoomName,
                "This is a description.",
                "-8.055513,-34.951541",
                R.drawable.rock_symbol
            )
        )
        view.showAvaiableRooms(mockRooms)
    }

    fun storeCoordinates(coordinates : String) =
        context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)
        .edit()
        .putString(USER_DISTANCE, coordinates)
        .apply()

    override fun getCoordinates() {
        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    storeCoordinates(
                        location.latitude.toString() + ',' + location.longitude.toString()
                    )
                }
            } .addOnFailureListener {}
    }
}
