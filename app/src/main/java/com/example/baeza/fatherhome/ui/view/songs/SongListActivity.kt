package com.example.baeza.fatherhome.ui.view.songs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.baeza.fatherhome.R
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    private val mSongListFragment = SongListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        initFragment()
        initToolbar(getString(R.string.hymn_list))
    }

    fun initFragment(){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mSongListFragment)
                    .commit()
    }

    fun initToolbar(title: String){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }
}
