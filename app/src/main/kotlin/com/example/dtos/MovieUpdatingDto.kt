package com.example.dtos

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class MovieUpdatingDto(
    @get:NotNull
    val id: UUID,
    @get:NotBlank
    val name: String,
    @get:NotBlank
    val originalTitle: String,
    @get:NotNull
    @get:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val releaseYear: LocalDate,
    val synopsis: String,
    @get:NotBlank
    val director: String,
    @get:NotEmpty
    val casts: List<String>
)
