package com.example.factories

import com.example.entities.Movie
import com.example.entities.MovieEntity
import java.time.LocalDate
import java.util.UUID

object MovieFactory {

    fun create(
        id: UUID = UUID.randomUUID(),
        name: String,
        originalTitle: String,
        releaseYear: LocalDate,
        synopsis: String,
        director: String,
        casts: List<String>
    ): Movie = MovieEntity(
        id = id,
        name = name,
        originalTitle = originalTitle,
        releaseYear = releaseYear,
        synopsis = synopsis,
        director = director,
        casts = casts
    )
}