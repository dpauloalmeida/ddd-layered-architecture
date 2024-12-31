package com.example.services

import com.example.entities.Movie
import com.example.filter.MovieByDirectorFilter
import com.example.filter.MovieByActorIsActingFilter
import com.example.filter.MovieByYearFilter

fun List<Movie>.filterByDirector(filter: String) = MovieByDirectorFilter().apply(this, filter)

fun List<Movie>.filterByActorIsActing(filter: String) = MovieByActorIsActingFilter().apply(this, filter)

fun List<Movie>.filterByYear(filter: Int) = MovieByYearFilter().apply(this, filter)