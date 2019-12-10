package com.example.deejai.RoomSelection
import android.Manifest
import android.content.pm.PackageManager
import com.example.deejai.Constants.LOCALIZATION_CODE
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class SelectRoomActivity : AppCompatActivity(), ViewModel {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCALIZATION_CODE)
        }
        else{
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    Log.d("LocalizationString", location.toString())
                }
                .addOnFailureListener {
                    throw Exception(it)
                }
        }
    }

    override fun showAvaiableRooms(rooms: List<Room>) {
        val adapter = RoomAdapter(rooms, this)
        roomList.adapter = adapter
    }

}
