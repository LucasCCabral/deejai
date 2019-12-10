package com.example.deejai.RoomSelection

class Room(
    val name : String,
    val description : String,
    val coordinates : String,
    val picture : Int ) {

    fun getDistance() = coordinates

}
