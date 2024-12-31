package com.example.dtos

import com.example.entities.Movie
import com.example.factories.MovieFactory

internal fun Movie.toDto() = MovieDescriptorDto(
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear.year,
    synopsis = synopsis,
    director = director,
    casts = casts
)

internal fun MovieCreationDto.toDomain() = MovieFactory.create(
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear,
    synopsis = synopsis,
    director = director,
    casts = casts
)

internal fun MovieUpdatingDto.toDomain() = MovieFactory.create(
    id = id,
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear,
    synopsis = synopsis,
    director = director,
    casts = casts
)