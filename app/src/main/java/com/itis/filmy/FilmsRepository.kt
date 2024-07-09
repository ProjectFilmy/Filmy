package com.itis.filmy

import androidx.room.Delete
import androidx.room.Update

object FilmsRepository {

    fun insertFilm(film: Film){
        films.add(film)
    }

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
    var id: Int = 2;
    val films: MutableList<Film> = mutableListOf(
        Film(0,
            "Planned",
            "PlannedFilm",
            "Крутой",
            "12.03.2004",
            "",
            0.0f,
            "https://fotovdom.ru/upload/iblock/928/928025cf31b071ce8889e6d4f1ee346a.jpg",

        ),
        Film(1,
            "Watched",
            "WatchedFilm",
            "Страшный",
            "3.12.2009",
            "Очень понравился",
            2.5f,
            "https://fotovdom.ru/upload/iblock/928/928025cf31b071ce8889e6d4f1ee346a.jpg"
        )
    )
}