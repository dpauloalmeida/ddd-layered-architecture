package com.example

import com.example.repositories.MovieRepository
import com.example.services.MovieServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig(
    private val movieRepository: MovieRepository
) {

    @Bean
    fun filmService(): MovieService = MovieServiceImpl(movieRepository)
}