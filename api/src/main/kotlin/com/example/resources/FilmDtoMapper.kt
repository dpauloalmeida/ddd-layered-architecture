package com.example.resources

import com.example.dtos.MovieCreationDto
import com.example.dtos.MovieDescriptorDto
import com.example.dtos.MovieUpdatingDto
import com.example.resources.json.MovieCreationRequest
import com.example.resources.json.MovieDescriptorResponse
import com.example.resources.json.MovieUpdatingRequest
import java.util.UUID

fun MovieDescriptorDto.toResponse() = MovieDescriptorResponse(
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear,
    synopsis = synopsis,
    director = director,
    casts = casts.joinToString(", ")
)

fun MovieCreationRequest.toDto() = MovieCreationDto(
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear,
    synopsis = synopsis,
    director = director,
    casts = casts
)

fun MovieUpdatingRequest.toDto(id: UUID) = MovieUpdatingDto(
    id = id,
    name = name,
    originalTitle = originalTitle,
    releaseYear = releaseYear,
    synopsis = synopsis,
    director = director,
    casts = casts
)