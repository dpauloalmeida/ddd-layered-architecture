package com.example.resources.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MovieDescriptorResponse(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("original_title")
    val originalTitle: String,
    @JsonProperty("release_year")
    val releaseYear: Int,
    @JsonProperty("synopsis")
    val synopsis: String,
    @JsonProperty("director")
    val director: String,
    @JsonProperty("casts")
    val casts: String
)