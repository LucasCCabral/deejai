package com.example.deejai.RoomSelection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel

class SelectRoomActivity : AppCompatActivity(), ViewModel {

    lateinit var roomList : RecyclerView
    lateinit var presenter : SelectRoomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_room)
        val linearLayoutManager = LinearLayoutManager(this)
        roomList = findViewById(R.id.room_list)
        roomList.layoutManager = linearLayoutManager
        presenter = SelectRoomPresenter(this)
        presenter.listAvaiableRooms()
    }

    override fun showAvaiableRooms(rooms: List<Room>) {
        val adapter = RoomAdapter(rooms, this)
        roomList.adapter = adapter
    }

}
