package com.example.resources

import com.example.resources.json.MovieCreationRequest
import com.example.resources.json.MovieDescriptorResponse
import com.example.resources.json.MovieUpdatingRequest
import com.example.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.UUID

@RestController
class MovieResource(
    private val movieService: MovieService
) {

    @PostMapping("/movies")
    fun createMovie(
        @RequestBody request: MovieCreationRequest,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Unit> {

        val movieId = movieService.create(request.toDto())

        val uri = uriComponentsBuilder.path("/movies/{id}")
            .buildAndExpand(movieId)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/movies/{id}")
    fun movieById(
        @PathVariable id: UUID
    ): ResponseEntity<MovieDescriptorResponse> {

        val movie = movieService.retrieveById(id)
        return ResponseEntity.ok(movie.toResponse())
    }

    @PutMapping("/movies/{id}")
    fun updateMovie(
        @PathVariable id: UUID,
        @RequestBody request: MovieUpdatingRequest
    ): ResponseEntity<Unit> {

        movieService.update(request.toDto(id))
        return ResponseEntity.ok().build()
    }

    @GetMapping("/movies/by-director/{name}")
    fun moviesByDirector(
        @PathVariable name: String
    ): ResponseEntity<List<MovieDescriptorResponse>> {

        val films = movieService.retrieveAllByDirector(name)
        return ResponseEntity.ok(films.map { it.toResponse() })
    }

    @GetMapping("/movies/by-actor/{name}/acting")
    fun moviesByActorIsActing(
        @PathVariable name: String
    ): ResponseEntity<List<MovieDescriptorResponse>> {

        val films = movieService.retrieveAllByActorIsActing(name)
        return ResponseEntity.ok(films.map { it.toResponse() })
    }

    @GetMapping("/movies/release-year/{year}")
    fun movieReleaseByYear(
        @PathVariable year: Int
    ): ResponseEntity<List<MovieDescriptorResponse>> {

        val films = movieService.retrieveAllByYear(year)
        return ResponseEntity.ok(films.map { it.toResponse() })
    }
}
