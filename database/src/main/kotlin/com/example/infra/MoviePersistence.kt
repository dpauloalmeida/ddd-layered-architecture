package com.example.infra

import com.example.entities.Movie
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
internal data class MoviePersistence(
    @Id
    override val id: UUID,
    override val name: String,
    override val originalTitle: String,
    override val releaseYear: LocalDate,
    override val synopsis: String,
    override val director: String,

    @ElementCollection
    @CollectionTable(
        name = "casts",
        joinColumns = [
            JoinColumn(name = "film_id")
        ]
    )
    @Column(name = "cast")
    override val casts: List<String>
) : Movie
