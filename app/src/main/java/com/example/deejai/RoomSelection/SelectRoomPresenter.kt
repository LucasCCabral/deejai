package com.example.deejai.RoomSelection

import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.Presenter
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel

class SelectRoomPresenter(private val view: ViewModel) : Presenter {

    override fun listAvaiableRooms() {
        var mockRooms = ArrayList<Room>()
        mockRooms.add(Room("CInzeira Rockzeira", "123123123", R.drawable.rock_symbol))
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

}
