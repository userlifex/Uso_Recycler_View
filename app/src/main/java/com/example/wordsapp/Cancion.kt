package com.example.wordsapp

import java.io.Serializable

data class Cancion (
    val titulo: String,
    val urlImage: String,
    val yotubeId: String
): Serializable