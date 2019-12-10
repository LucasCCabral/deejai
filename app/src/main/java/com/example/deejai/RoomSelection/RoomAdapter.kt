package com.example.deejai.RoomSelection

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
        holder.description = rooms[position].description
    }

    override fun getItemCount() = rooms.size

    inner class RoomViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ROOM_INFO = "Room Info"
        private val EXIT = "exit"
        var description = "No description found."
        internal var cv: CardView
        internal var roomName : TextView
        internal var roomDistance : TextView
        internal var roomImage : ImageView
        internal var roomInfoButton : ImageButton

        init {
            cv = itemView.findViewById(R.id.room)
            roomName = itemView.findViewById(R.id.room_name)
            roomDistance = itemView.findViewById(R.id.room_distance)
            roomImage = itemView.findViewById(R.id.room_image) as ImageView
            roomInfoButton = itemView.findViewById(R.id.room_info_button)
            roomInfoButton.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle(ROOM_INFO)
                    .setMessage(description)
                    .setNegativeButton(EXIT, null)
                    .show()
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, RoomPlayer::class.java)
            intent.putExtra(ROOM_NAME_KEY, roomName.text.toString())
            context.startActivity(intent)
        }
    }

}
