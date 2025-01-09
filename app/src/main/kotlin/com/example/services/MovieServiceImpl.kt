package com.example.services

import com.example.*
import com.example.dtos.*
import com.example.dtos.toDto
import com.example.repositories.MovieRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import java.util.*

@Validated
internal class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val publisher: DomainEvent
): MovieService {

    @Transactional
    override fun create(dto: MovieCreationDto): UUID {
        val movie = dto.toDomain()
        movieRepository.save(movie)
        publisher.movieWasCreated(movie)
        return movie.id
    }

    @Transactional
    override fun update(dto: MovieUpdatingDto) {
        movieRepository.findById(dto.id).map {
            movieRepository.save(dto.toDomain())
            publisher.movieWasUpdated(it)
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