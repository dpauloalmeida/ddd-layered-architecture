package com.example.services

import com.example.*
import com.example.dtos.*
import com.example.dtos.toDto
import com.example.repositories.MovieRepository
import org.springframework.validation.annotation.Validated
import java.util.*

@Validated
internal class MovieServiceImpl(
    private val movieRepository: MovieRepository
): MovieService {

    override fun create(dto: MovieCreationDto): UUID {
        val film = dto.toDomain()
        movieRepository.save(film)
        return film.id
    }

    override fun update(dto: MovieUpdatingDto) {
        movieRepository.findById(dto.id).map {
            movieRepository.save(dto.toDomain())
        }.orElseThrow{ EntityNotFoundException() }
    }

    override fun retrieveById(id: UUID): MovieDescriptorDto {
        val film = movieRepository.findById(id)

        return film.map { it.toDto() }
            .orElseThrow { EntityNotFoundException() }
    }

    override fun retrieveAllByDirector(director: String): List<MovieDescriptorDto> {
        val films = movieRepository.findAll()
        return films.filterByDirector(director)
            .map { it.toDto() }
    }

    override fun retrieveAllByActorIsActing(actor: String): List<MovieDescriptorDto> {
        val films = movieRepository.findAll()
        return films.filterByActorIsActing(actor)
            .map { it.toDto() }
    }

    override fun retrieveAllByYear(year: Int): List<MovieDescriptorDto> {
        val films = movieRepository.findAll()
        return films.filterByYear(year)
            .map { it.toDto() }
    }
}