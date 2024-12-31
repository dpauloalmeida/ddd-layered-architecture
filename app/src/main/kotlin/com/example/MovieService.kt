package com.example

import com.example.dtos.MovieCreationDto
import com.example.dtos.MovieDescriptorDto
import com.example.dtos.MovieUpdatingDto
import java.util.*
import javax.validation.Valid

interface MovieService {

    fun create(@Valid dto: MovieCreationDto): UUID

    fun update(@Valid dto: MovieUpdatingDto)

    fun retrieveById(id: UUID): MovieDescriptorDto

    fun retrieveAllByDirector(director: String): List<MovieDescriptorDto>

    fun retrieveAllByActorIsActing(actor: String): List<MovieDescriptorDto>

    fun retrieveAllByYear(year: Int): List<MovieDescriptorDto>
}