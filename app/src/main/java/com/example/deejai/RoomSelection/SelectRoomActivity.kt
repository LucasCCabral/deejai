package com.example.deejai.RoomSelection
import android.Manifest
import android.content.pm.PackageManager
import com.example.deejai.Constants.LOCALIZATION_CODE
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.R
import com.example.deejai.RoomSelection.SelectRoomContract.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.jetbrains.anko.*
class SelectRoomActivity : AppCompatActivity(), ViewModel {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var roomList : RecyclerView
    lateinit var presenter : SelectRoomPresenter
    var location_global : Location = Location("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doAsync {
            if (!checkPermission()) {
                requestPermission()
            }
            else {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@SelectRoomActivity)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            location_global = location
                            //tem que ver como vai armazenar o objeto \/
                            presenter.storeCoordinates(location.latitude.toString() + ',' + location.longitude.toString())
                            setContentView(R.layout.select_room)
                            val linearLayoutManager = LinearLayoutManager(this@SelectRoomActivity)
                            roomList = findViewById(R.id.room_list)
                            roomList.layoutManager = linearLayoutManager
                            presenter = SelectRoomPresenter(this@SelectRoomActivity, this@SelectRoomActivity, location)
                            presenter.listAvaiableRooms()
                        }
                    }
                    .addOnFailureListener {
                        throw Exception(it)
                    }
            }
            uiThread {
                setContentView(R.layout.select_room)
                val linearLayoutManager = LinearLayoutManager(this@SelectRoomActivity)
                roomList = findViewById(R.id.room_list)
                roomList.layoutManager = linearLayoutManager
                presenter = SelectRoomPresenter(this@SelectRoomActivity, this@SelectRoomActivity, location_global)
                presenter.listAvaiableRooms()
            }
        }
    }

    override fun showAvaiableRooms(rooms: List<Room>) {
        val adapter = RoomAdapter(rooms, this)
        roomList.adapter = adapter
    }

    fun checkPermission () : Boolean{
        return ContextCompat.checkSelfPermission(this@SelectRoomActivity,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this@SelectRoomActivity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCALIZATION_CODE)
    }

}
