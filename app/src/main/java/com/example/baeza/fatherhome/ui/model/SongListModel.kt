package com.example.baeza.fatherhome.ui.model

import android.content.Context
import com.example.baeza.fatherhome.R
import com.example.baeza.fatherhome.ui.manager.SongListManager

class SongListModel (var presenter: SongListManager.Presenter, var context: Context?) : SongListManager.Model {

    var authorArray = context?.resources?.getStringArray(R.array.array_song_author)
    var textSongArray = context?.resources?.getStringArray(R.array.array_song_text)
    var titleSongArray = context?.resources?.getStringArray(R.array.array_song_title)
    var licenseSongArray = context?.resources?.getStringArray(R.array.array_song_license)
    var songList: ArrayList<Song> = ArrayList()

    override fun getSongListModel() {
        presenter.getSongListView(builtListSongs(authorArray, titleSongArray, textSongArray, licenseSongArray))
    }

    fun builtListSongs(authorArray: Array<String>?, titleSongArray: Array<String>?, textSong: Array<String>?, licenseSong: Array<String>?): List<Song>{
        for (i in authorArray?.indices!!){
            songList.add(Song(authorArray[i], titleSongArray!![i], textSong!![i], licenseSong!![i]))
        }
        return songList
    }
}