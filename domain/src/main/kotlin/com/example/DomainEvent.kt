package com.example

import com.example.entities.Movie

interface DomainEvent {
    fun movieWasCreated(movie: Movie)
    fun movieWasUpdated(movie: Movie)
}