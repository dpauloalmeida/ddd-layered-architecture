package com.example.infra

import com.example.entities.Movie
import com.example.factories.MovieFactory

internal fun Movie.toPersistence() = MoviePersistence(
    id = id,
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear,
    synopsis = synopsis,
    director = director,
    casts = casts
)
