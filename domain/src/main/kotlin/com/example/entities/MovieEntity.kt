package com.example.entities

import java.time.LocalDate
import java.util.UUID

internal data class MovieEntity(
    override val id: UUID,
    override val name: String,
    override val originalTitle: String,
    override val releaseYear: LocalDate,
    override val synopsis: String,
    override val director: String,
    override val casts: List<String>
) : Movie