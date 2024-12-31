package com.example.filter

import com.example.entities.Movie

class MovieByDirectorFilter : Filter<Movie, String> {

    override fun apply(list: List<Movie>, filter: String): List<Movie> {
        return list.filter { it.director == filter }
    }
}