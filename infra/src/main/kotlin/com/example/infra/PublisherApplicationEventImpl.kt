package com.example.infra

import com.example.DomainEvent
import com.example.entities.Movie
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class PublisherApplicationEventImpl private constructor(
    private val producer: KafkaProducer,
    private val mapper: ObjectMapper
) : DomainEvent {

    override fun movieWasCreated(movie: Movie) {
        producer.sendMessage(
            "topic.movie.created",
            movie.originalTitle,
            mapper.writeValueAsString(movie)
        )
    }

    override fun movieWasUpdated(movie: Movie) {
        producer.sendMessage(
            "topic.movie.updated",
            movie.originalTitle,
            mapper.writeValueAsString(movie)
        )
    }
}