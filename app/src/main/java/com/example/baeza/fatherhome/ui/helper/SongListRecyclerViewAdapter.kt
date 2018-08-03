package com.example.baeza.fatherhome.ui.helper

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.baeza.fatherhome.R
import com.example.baeza.fatherhome.ui.model.Song
import com.example.baeza.fatherhome.ui.view.songs.SongDetailActivity

class SongListRecyclerViewAdapter (var songList: List<Song>?, var context: Context?): RecyclerView.Adapter<SongListRecyclerViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_song_card, parent, false))
    }

    override fun getItemCount(): Int { return songList?.size?:0 }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewSongTitle?.text = songList?.get(position)?.titleSong
        holder.textViewSongBand?.text = songList?.get(position)?.authorSong

        holder.itemView.setOnClickListener{
                    var intent = Intent (context, SongDetailActivity::class.java)
        intent?.putExtra(SongDetailActivity().KEY_SONG_EXTRA, songList?.get(position))
        context?.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        val textViewSongTitle = itemView?.findViewById<TextView>(R.id.textViewSongTitle)
        val textViewSongBand = itemView?.findViewById<TextView>(R.id.textViewSongBand)
    }
}