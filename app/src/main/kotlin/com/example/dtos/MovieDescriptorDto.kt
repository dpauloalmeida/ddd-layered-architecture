package com.example.dtos

data class MovieDescriptorDto(
    val name: String,
    val originalTitle: String,
    val releaseYear: Int,
    val synopsis: String,
    val director: String,
    val casts: List<String>
)
