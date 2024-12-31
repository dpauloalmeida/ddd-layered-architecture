package com.example.filter

import com.example.entities.Movie

class MovieByYearFilter : Filter<Movie, Int> {

    override fun apply(list: List<Movie>, filter: Int): List<Movie> {
        return list.filter { it.releaseYear.year == filter }
    }
}