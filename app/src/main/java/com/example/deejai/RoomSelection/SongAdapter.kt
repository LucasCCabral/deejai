package com.example.deejai.RoomSelection

import Song
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.deejai.R
import kotlinx.android.synthetic.main.list_row.view.*

class SongAdapter (private val songs: ArrayList<Song>, private val c : Context) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun getItemCount(): Int = songs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = songs[position]
        holder.name.text = "Song id: " + p.id
        holder.id.text = "Song name: " + p.name
    }

    class ViewHolder (item : View) : RecyclerView.ViewHolder(item){
        var name = item.title
        var id = item.songid
    }
}