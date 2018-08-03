package com.example.baeza.fatherhome.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(var authorSong: String?, var titleSong: String?, var textSong: String, var licenseSong:String) : Parcelable {}