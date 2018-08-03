package com.example.baeza.fatherhome.ui.presenter

import android.content.Context
import com.example.baeza.fatherhome.ui.manager.SongListManager
import com.example.baeza.fatherhome.ui.model.Song
import com.example.baeza.fatherhome.ui.model.SongListModel

class SongListPresenter (var view: SongListManager.View, var context: Context?) : SongListManager.Presenter {

    override fun getSongListView(songList: List<Song>) {
        view.getSongListView(songList)
    }

    var mModel = SongListModel(this, context)
    override fun getSongListModel() {
        //here to add the language
        mModel.getSongListModel()
    }
}