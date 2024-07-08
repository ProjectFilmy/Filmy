package com.itis.filmy

import java.time.Year

data class Film(
    val id: Int,
    val type: String,
    val name: String,
    val genre: String,
    val date: String,
    val comment: String,
    val rating: Float,
    val url: String
) {

}