package com.itis.filmy


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
    override fun toString():String{
        return "$id $type $name $genre $date $comment $rating $url"
    }


}