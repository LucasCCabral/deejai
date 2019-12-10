package com.example.deejai.RoomSelection

import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.Presenter
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel

class SelectRoomPresenter(private val view: ViewModel) : Presenter {
    var mockRooms = ArrayList<Room>()

    override fun listAvaiableRooms() {
        view.showAvaiableRooms(mockRooms)
    }

    override fun addNewRoom(newRoomName: String) {
        mockRooms.add(Room(newRoomName, "This is a description.", "1231231", R.drawable.rock_symbol))
        view.showAvaiableRooms(mockRooms)
    }

}
