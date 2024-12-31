package com.example.resources.json

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class MovieCreationRequest(
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("original_title")
    val originalTitle: String = "",
    @JsonProperty("release_year")
    val releaseYear: LocalDate,
    @JsonProperty("synopsis")
    val synopsis: String = "",
    @JsonProperty("director")
    val director: String = "",
    @JsonProperty("casts")
    val casts: List<String> = listOf()
)
