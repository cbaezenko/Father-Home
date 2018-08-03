package com.example.baeza.fatherhome.ui.view.songs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.baeza.fatherhome.R
import com.example.baeza.fatherhome.ui.model.Song
import kotlinx.android.synthetic.main.activity_song_detail.*

class SongDetailActivity : AppCompatActivity() {

    val KEY_SONG_EXTRA: String = "key_song_extra"
    var songDetailFragment: SongDetailFragment = SongDetailFragment()
    var mSong:Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)
        mSong = intent?.extras?.getParcelable(KEY_SONG_EXTRA)

        initToolbar(mSong?.titleSong?:getString(R.string.toolbar_title_song_detail))
        initFragment()
    }

    private fun initFragment(){
        var bundle = Bundle()
        bundle.putParcelable(KEY_SONG_EXTRA, mSong)
        songDetailFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, songDetailFragment).commit()
    }

    private fun  initToolbar(toolbarTitle: String){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = toolbarTitle
    }
}
