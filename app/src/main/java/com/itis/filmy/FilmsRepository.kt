package com.itis.filmy

object FilmsRepository {

    fun deleteFilm(id : Int){
        for (i in 0 until  films.size){
            if (films.get(i).id == id){
                films.removeAt(i)
                break
            }
        }
    }


    fun updateFilm(film: Film){
        for (i in 0 until  films.size){
            if (films.get(i).id == film.id){
                films[i] = film
                break
            }
        }
    }
    val films: MutableList<Film> = mutableListOf(
    )
}