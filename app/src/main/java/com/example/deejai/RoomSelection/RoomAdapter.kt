package com.example.deejai.RoomSelection

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.Constants.ROOM_NAME_KEY
import com.example.deejai.R

class RoomAdapter(private val rooms : List<Room>, private val context : Context) : Adapter<RoomAdapter.RoomViewHolder>() {

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

    inner class RoomViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var cv: CardView
        internal var roomName : TextView
        internal var roomDistance : TextView
        internal var roomImage : ImageView

        init {
            cv = itemView.findViewById(R.id.room)
            roomName = itemView.findViewById(R.id.room_name)
            roomDistance = itemView.findViewById(R.id.room_distance)
            roomImage = itemView.findViewById(R.id.room_image) as ImageView
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, RoomPlayer::class.java)
            intent.putExtra(ROOM_NAME_KEY, roomName.text.toString())
            context.startActivity(intent)
        }
    }

}
