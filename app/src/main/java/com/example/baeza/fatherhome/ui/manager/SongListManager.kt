package com.example.baeza.fatherhome.ui.manager

import com.example.baeza.fatherhome.ui.model.Song

interface SongListManager {
    interface Model{
        fun getSongListModel()
    }
    interface View{
        fun getSongListView(songList: List<Song>)
    }
    interface Presenter{
        fun getSongListModel()
        fun getSongListView(songList: List<Song>)
    }
}