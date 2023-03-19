package com.example.familyguycharacters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    val name: String,

    val description: String,
    val photo: String,
    val episode: String,
    val fullName: String,
    val age: String,
    val voice: String
):Parcelable
