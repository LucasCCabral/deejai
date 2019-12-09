package com.example.deejai.RoomSelection

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import com.example.deejai.R

class RoomAdapter(val rooms : List<Room>) : Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): RoomViewHolder {
        val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false)
        return RoomViewHolder(v)
    }

    override fun onBindViewHolder(holder : RoomViewHolder, position : Int) {
        holder.roomName.text = rooms[position].name
        holder.roomDistance.text = rooms[position].coordinates
        holder.roomImage.setImageResource(rooms[position].picture)
    }

    override fun getItemCount() = rooms.size

    class RoomViewHolder internal constructor(itemView: View) :
    ViewHolder(itemView) {
        internal var cv: CardView
        internal var roomName : TextView
        internal var roomDistance : TextView
        internal var roomImage : ImageView

        init {
            cv = itemView.findViewById(R.id.room)
            roomName = itemView.findViewById(R.id.room_name)
            roomDistance = itemView.findViewById(R.id.room_distance)
            roomImage = itemView.findViewById(R.id.room_image) as ImageView
        }
    }

}
