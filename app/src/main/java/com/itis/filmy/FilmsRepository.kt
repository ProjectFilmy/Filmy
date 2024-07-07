package com.itis.filmy

object FilmsRepository {
    val films: List<Film> = listOf(
        Film(0,
            "planned",
            "PlannedFilm",
            "https://fotovdom.ru/upload/iblock/928/928025cf31b071ce8889e6d4f1ee346a.jpg"
        ),
        Film(1,
            "watched",
            "WatchedFilm",
            "https://fotovdom.ru/upload/iblock/928/928025cf31b071ce8889e6d4f1ee346a.jpg"
        )
    )
}