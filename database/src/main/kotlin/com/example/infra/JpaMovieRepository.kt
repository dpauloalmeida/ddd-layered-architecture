package com.example.infra

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal interface JpaMovieRepository : JpaRepository<MoviePersistence, UUID>
