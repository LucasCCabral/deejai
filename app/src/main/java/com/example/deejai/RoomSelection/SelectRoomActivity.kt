package com.example.deejai.RoomSelection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.AddRoomActivity
import com.example.deejai.Constants.NEW_ROOM_RESULT_CODE
import com.example.deejai.Constants.ROOM_NAME
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel
import kotlinx.android.synthetic.main.select_room.*

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
        add_room_button.setOnClickListener {
            val intent = Intent(this, AddRoomActivity::class.java)
            startActivityForResult(intent, NEW_ROOM_RESULT_CODE)
        }
    }

    override fun showAvaiableRooms(rooms: List<Room>) {
        val adapter = RoomAdapter(rooms, this)
        roomList.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == NEW_ROOM_RESULT_CODE) {
            val newRoomName = data?.getStringExtra(ROOM_NAME)
            if(newRoomName != null)
                presenter.addNewRoom(newRoomName)
        }
    }

}
