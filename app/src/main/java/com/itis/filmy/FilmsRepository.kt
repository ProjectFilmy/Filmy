package com.itis.filmy

object FilmsRepository {
    val films: MutableList<Film> = mutableListOf(
        Film(0,
            "planned",
            "PlannedFilm",
            "Крутой",
            "12.03.2004",
            "",
            0.0f,
            "https://fotovdom.ru/upload/iblock/928/928025cf31b071ce8889e6d4f1ee346a.jpg",

        ),
        Film(1,
            "watched",
            "WatchedFilm",
            "Страшный",
            "3.12.2009",
            "Очень понравился",
            2.5f,
            "https://fotovdom.ru/upload/iblock/928/928025cf31b071ce8889e6d4f1ee346a.jpg"
        )
    )
}