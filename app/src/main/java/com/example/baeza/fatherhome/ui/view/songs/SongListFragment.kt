package com.example.baeza.fatherhome.ui.view.songs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.baeza.fatherhome.R
import com.example.baeza.fatherhome.ui.helper.SongListRecyclerViewAdapter
import com.example.baeza.fatherhome.ui.manager.SongListManager
import com.example.baeza.fatherhome.ui.model.Song
import com.example.baeza.fatherhome.ui.presenter.SongListPresenter
import kotlinx.android.synthetic.main.fragment_song_list.*

class SongListFragment : Fragment(), SongListManager.View {

    var songList: List<Song>? = null

    override fun getSongListView(songList: List<Song>) { this.songList = songList }

    var presenter: SongListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SongListPresenter(this, context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter?.mModel?.getSongListModel()
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = SongListRecyclerViewAdapter(songList, context)
    }

    fun requestData() { presenter?.getSongListModel() }
}
