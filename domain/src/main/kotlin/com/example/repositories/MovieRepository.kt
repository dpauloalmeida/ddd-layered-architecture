package com.example.repositories

import com.example.entities.Movie
import java.util.*

interface MovieRepository {
    fun findAll(): List<Movie>
    fun findById(id: UUID): Optional<Movie>
    fun save(movie: Movie)
}