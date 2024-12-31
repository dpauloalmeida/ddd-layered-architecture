package com.example.infra

import com.example.entities.Movie
import com.example.repositories.MovieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MovieRepositoryImpl private constructor(
    private val repository: JpaMovieRepository
) : MovieRepository {

    override fun findAll(): List<Movie> {
        return repository.findAll()
    }

    override fun findById(id: UUID): Optional<Movie> {
        return Optional.ofNullable(repository.findByIdOrNull(id))
    }

    override fun save(movie: Movie) {
        repository.save(movie.toPersistence())
    }
}