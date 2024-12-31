package com.example.entities

import java.time.LocalDate
import java.util.UUID

interface Movie {
    val id: UUID
    val name: String
    val originalTitle: String
    val releaseYear: LocalDate
    val synopsis: String
    val director: String
    val casts: List<String>
}