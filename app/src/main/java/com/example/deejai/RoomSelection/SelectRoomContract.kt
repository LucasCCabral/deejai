package com.example.deejai.RoomSelection

import com.example.deejai.Data.Room

interface SelectRoomContract {

    interface ViewModel {
        fun showAvaiableRooms(rooms : List<Room>)

    }

    interface Presenter {
        fun listAvaiableRooms()
        fun addNewRoom(newRoomName: String)
        fun getCoordinates()
    }

}
