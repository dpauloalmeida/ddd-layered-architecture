package com.example.filter

import com.example.entities.Movie
import java.time.LocalDate

class MovieByActorIsActingFilter : Filter<Movie, String> {

    override fun apply(list: List<Movie>, filter: String): List<Movie> {
        return list.filter { it.casts.contains(filter) && it.releaseYear.year == LocalDate.now().year }
    }
}