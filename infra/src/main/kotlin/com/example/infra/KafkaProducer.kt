package com.example.infra

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun sendMessage(topic: String, key: String, message: String) {
        kafkaTemplate.send(topic, message)
    }
}