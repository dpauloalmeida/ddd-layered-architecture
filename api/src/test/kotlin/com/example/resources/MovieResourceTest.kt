package com.example.resources

import io.restassured.RestAssured
import io.restassured.parsing.Parser
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(partitions = 1, topics = ["topic.movie.created"], ports = [9092])
class MovieResourceTest {


    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setupRestAssuredPort() {
        RestAssured.port = port
        RestAssured.defaultParser = Parser.JSON
    }

    @Test
    fun `should create a movie and return 201`() {
        val request = """
            {
                "name": "Inception",
                "original_title": "Inception",
                "release_year": "2010-07-16",
                "synopsis": "A thief who steals corporate secrets through dream-sharing technology.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        RestAssured.given()
            .contentType("application/json")
            .body(request)
            .`when`()
            .post("/movies")
            .then()
            .statusCode(201)
    }

    @Test
    fun `should update a movie and return 200`() {
        val createRequest = """
            {
                "name": "Inception",
                "original_title": "Inception",
                "release_year": "2010-07-16",
                "synopsis": "A thief who steals corporate secrets through dream-sharing technology.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        val response = RestAssured.given()
            .contentType("application/json")
            .body(createRequest)
            .`when`()
            .post("/movies")
            .then()
            .statusCode(201)
            .extract()
            .response()

        val location = response.getHeader("Location")
        val movieId = location.substringAfterLast("/")

        val updateRequest = """
            {
                "name": "Inception Updated",
                "original_title": "Inception",
                "release_year": "2010-07-16",
                "synopsis": "An updated synopsis.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        RestAssured.given()
            .contentType("application/json")
            .body(updateRequest)
            .`when`()
            .put("/movies/$movieId")
            .then()
            .statusCode(200)

        RestAssured.given()
            .`when`()
            .get("/movies/$movieId")
            .then()
            .statusCode(200)
            .body("name", org.hamcrest.Matchers.equalTo("Inception Updated"))
            .body("synopsis", org.hamcrest.Matchers.equalTo("An updated synopsis."))
    }

    @Test
    fun `should retrieve a movie by ID and return 200`() {
        val createRequest = """
            {
                "name": "Inception",
                "original_title": "Inception",
                "release_year": "2010-07-16",
                "synopsis": "A thief who steals corporate secrets through dream-sharing technology.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        val response = RestAssured.given()
            .contentType("application/json")
            .body(createRequest)
            .`when`()
            .post("/movies")
            .then()
            .statusCode(201)
            .extract()
            .response()

        val location = response.getHeader("Location")
        val movieId = location.substringAfterLast("/")

        RestAssured.given()
            .`when`()
            .get("/movies/$movieId")
            .then()
            .statusCode(200)
            .body("name", org.hamcrest.Matchers.equalTo("Inception"))
            .body("synopsis", org.hamcrest.Matchers.equalTo("A thief who steals corporate secrets through dream-sharing technology."))
            .body("director", org.hamcrest.Matchers.equalTo("Christopher Nolan"))
            .body("casts", org.hamcrest.Matchers.equalTo("Leonardo DiCaprio, Joseph Gordon-Levitt"))
            .body("release_year", org.hamcrest.Matchers.equalTo(2010))
            .body("original_title", org.hamcrest.Matchers.equalTo("Inception"))
    }

    @Test
    fun `should retrieve all movies by director and return 200`() {
        val createRequest = """
            {
                "name": "Inception",
                "original_title": "Inception",
                "release_year": "2010-07-16",
                "synopsis": "A thief who steals corporate secrets through dream-sharing technology.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        RestAssured.given()
            .contentType("application/json")
            .body(createRequest)
            .`when`()
            .post("/movies")
            .then()
            .statusCode(201)

        RestAssured.given()
            .`when`()
            .get("/movies/by-director/Christopher Nolan")
            .then()
            .statusCode(200)
            .body("size()", org.hamcrest.Matchers.greaterThan(0))
            .body("[0].director", org.hamcrest.Matchers.equalTo("Christopher Nolan"))
            .body("[0].name", org.hamcrest.Matchers.equalTo("Inception"))
    }

    @Test
    fun `should retrieve all movies by actor and return 200`() {
        val createRequest = """
            {
                "name": "Inception",
                "original_title": "Inception",
                "release_year": "2025-07-16",
                "synopsis": "A thief who steals corporate secrets through dream-sharing technology.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        RestAssured.given()
            .contentType("application/json")
            .body(createRequest)
            .`when`()
            .post("/movies")
            .then()
            .statusCode(201)

        RestAssured.given()
            .`when`()
            .get("/movies/by-actor/Leonardo DiCaprio/acting")
            .then()
            .statusCode(200)
            .body("size()", org.hamcrest.Matchers.greaterThan(0))
            .body("[0].casts", org.hamcrest.Matchers.containsString("Leonardo DiCaprio"))
    }

    // should retrieve all movies by release year and return 200
    @Test
    fun `should retrieve all movies by release year and return 200`() {
        val createRequest = """
            {
                "name": "Inception",
                "original_title": "Inception",
                "release_year": "2010-07-16",
                "synopsis": "A thief who steals corporate secrets through dream-sharing technology.",
                "director": "Christopher Nolan",
                "casts": ["Leonardo DiCaprio", "Joseph Gordon-Levitt"]
            }
        """.trimIndent()

        RestAssured.given()
            .contentType("application/json")
            .body(createRequest)
            .`when`()
            .post("/movies")
            .then()
            .statusCode(201)

        RestAssured.given()
            .`when`()
            .get("/movies/release-year/2010")
            .then()
            .statusCode(200)
            .body("size()", org.hamcrest.Matchers.greaterThan(0))
            .body("[0].release_year", org.hamcrest.Matchers.equalTo(2010))
    }

}