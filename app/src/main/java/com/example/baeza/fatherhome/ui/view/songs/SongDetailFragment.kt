package com.example.baeza.fatherhome.ui.view.songs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.baeza.fatherhome.R
import com.example.baeza.fatherhome.ui.model.Song
import kotlinx.android.synthetic.main.fragment_song_detail.*

class SongDetailFragment : Fragment() {

    var mSong:Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mSong = arguments?.getParcelable(SongDetailActivity().KEY_SONG_EXTRA)
        return inflater.inflate(R.layout.fragment_song_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        textSong.text = mSong?.textSong
        authorSong.text = mSong?.authorSong
        titleSong.text = mSong?.titleSong
        songLicence.text = mSong?.licenseSong
    }
}
