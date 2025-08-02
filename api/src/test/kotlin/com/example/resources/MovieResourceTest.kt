package com.example.resources

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieResourceTest {

    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setupRestAssuredPort() {
        RestAssured.port = port
    }

    @Test
    fun `should return 404 for root endpoint`() {
        RestAssured.get("/")
            .then()
            .statusCode(404)
    }

}