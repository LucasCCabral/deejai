package com.example.deejai.RoomSelection

import android.content.Context
import android.location.Location
import com.example.deejai.Constants
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.Presenter
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel

class SelectRoomPresenter(private val view: ViewModel, private val context : Context, private val currentCoordinates : Location) : Presenter {

    override fun listAvaiableRooms() {
        var mockRooms = ArrayList<Room>()
        var mockLocation = Location("")
        mockLocation.latitude = -8.055513
        mockLocation.longitude = -34.951541

        val temp: Float = (currentCoordinates.distanceTo(mockLocation)/1000)
        val myvalue = String.format("%.1f", temp)

        mockRooms.add(Room("CInzeira Rockzeira",  myvalue + "km", R.drawable.rock_symbol))
        mockRooms.add(Room("Breguinha Da Rapeize", "123123", R.drawable.brega_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        mockRooms.add(Room("Cool guys jazz", "123123", R.drawable.jazz_symbol))
        view.showAvaiableRooms(mockRooms)
    }
    override fun storeCoordinates(coordinates : String) =
        context.getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
            .edit()
            .putString(Constants.USER_DISTANCE, coordinates)
            .apply()

    fun getCoordinates() =
        context.getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
            .getString(Constants.USER_DISTANCE, Constants.NO_COORDINATES)
}
